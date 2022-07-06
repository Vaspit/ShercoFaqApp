package com.example.shercofaqapp.view

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import coil.compose.rememberAsyncImagePainter
import com.canhub.cropper.*
import com.example.shercofaqapp.R
import com.example.shercofaqapp.model.User
import com.example.shercofaqapp.viewmodel.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth

class AccountFragment : Fragment() {

    lateinit var editor: SharedPreferences.Editor
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = ComposeView(requireContext())
        val sharedPref = requireActivity()
            .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        editor = sharedPref.edit()

        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        profileViewModel.readUser()
        profileViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            val bikeNamesList = arrayListOf<Any>("There are no bikes")

            if (user.bikes != null) {
                //Delete "There are no bikes" from bikeNamesList
                if (bikeNamesList.elementAt(0) == "There are no bikes") {
                    bikeNamesList.clear()
                }
                user.bikes!!.map {
                    bikeNamesList.add(it.value["bikeName"]!!)
                }
            }

            view.setContent {
                SetUI(user, bikeNamesList, profileViewModel)
            }

        })

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_menu_account, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.topMenuAccountLogOut -> {
                FirebaseAuth.getInstance().signOut()
                editor.putBoolean("isLoggedIn", false)
                editor.apply()
                findNavController()
                    .navigate(R.id.action_accountFragment_to_loginFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

@Composable
private fun SetUI(user: User, bikeNamesList: ArrayList<Any>, profileViewModel: ProfileViewModel) {

    val rowHeight = 128.dp

    Box(
        modifier = Modifier
            .background(colorResource(id = R.color.background_white_grey))
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {

            Column {

                Box() {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        profileImage(user.userProfileImageUrl!!, rowHeight)
                    }

<<<<<<< HEAD
                    changeProfileImageButton(profileViewModel)
=======
                    editPhotoButton()
>>>>>>> 4d717af00f34ecbc82747c6fc14a35d6d31cea95
                }

                Row() {

                    textFields(
                        user = user,
                        bikeNamesList = bikeNamesList)
                }
            }
        }
    }
}

@Composable
private fun textFields(user: User, bikeNamesList: ArrayList<Any>) {
    Column(
        modifier = Modifier
            .padding(top = 16.dp, start = 8.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        userNameField(user)
        userEmailField(user)
        userBikesField(bikeNamesList)
    }
}

@Composable
private fun userNameField(user: User) {
    Column(
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
    ) {
        Text(
            text = "Your name",
            fontSize = 18.sp,
            fontWeight = FontWeight.Light,
            fontStyle = FontStyle.Italic
        )

        Text(
            text = user.userName!!,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun userEmailField(user: User) {
    Column(
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
    ) {
        Text(
            text = "Your e-mail",
            fontSize = 18.sp,
            fontWeight = FontWeight.Light,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = user.userEmail!!,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun userBikesField(bikeNamesList: ArrayList<Any>) {
    Column(
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
    ) {
        Text(
            text = "Your bikes",
            fontSize = 18.sp,
            fontWeight = FontWeight.Light,
            fontStyle = FontStyle.Italic
        )

        for (item in bikeNamesList) {
            Text(
                text = item.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun profileImage(userProfileImageUrl: String, rowHeight: Dp) {

    if (userProfileImageUrl == "") {
        Image(
            painter = painterResource(id = R.drawable.default_profile_icon),
            contentDescription = "userProfileImage",
            modifier = Modifier
                .padding(8.dp)
                .size(rowHeight)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = Color.Blue,
                    shape = CircleShape
                ),
            contentScale = ContentScale.Crop
        )
    } else { //Load image from Firebase storage
        Image(
        painter = rememberAsyncImagePainter(userProfileImageUrl),
            contentDescription = "userProfileImage",
            modifier = Modifier
                .padding(8.dp)
                .size(rowHeight)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = Color.Blue,
                    shape = CircleShape
                ),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
<<<<<<< HEAD
private fun changeProfileImageButton(profileViewModel: ProfileViewModel) {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val imageCropLauncher = rememberLauncherForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            // use the cropped image
            imageUri = result.uriContent
            profileViewModel.getUserProfileImageUrl(imageUri!!)
        } else {
            // an error occurred cropping
            val exception = result.error
        }
    }

    Button(onClick = {
        imageCropLauncher.launch(options(uri = imageUri) {
            setCropShape(CropImageView.CropShape.OVAL)
            setGuidelines(CropImageView.Guidelines.ON)
            setOutputCompressFormat(Bitmap.CompressFormat.PNG)
        })
    }) {
        Text("Pick image to crop")
    }

=======
private fun editPhotoButton() {
    Image(
        painter = painterResource(R.drawable.ic_baseline_camera_alt_24),
        contentDescription = "putPhotoButton",
        modifier = Modifier
            .padding(start = 220.dp, top = 100.dp)
            .size(40.dp)
            .clip(CircleShape)
            .background(Color.Blue, CircleShape)
            .clickable {
                onEditPhotoButtonClick()
            },
        contentScale = ContentScale.Inside
    )
>>>>>>> 4d717af00f34ecbc82747c6fc14a35d6d31cea95
}

@Composable
private fun onEditPhotoButtonClick() {
    val context = LocalContext.current

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SetUI(user = User(
        userName = "Vaspit",
        userEmail = "vasvko@mail.ru"
    ), bikeNamesList = arrayListOf("Bike 1", "Bike 2", "Bike 3"), ProfileViewModel()
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview1() {
<<<<<<< HEAD
    changeProfileImageButton(ProfileViewModel())
=======
    editPhotoButton()
>>>>>>> 4d717af00f34ecbc82747c6fc14a35d6d31cea95
}
