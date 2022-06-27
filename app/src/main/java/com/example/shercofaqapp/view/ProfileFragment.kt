package com.example.shercofaqapp.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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
        profileViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            val bikeNamesList = arrayListOf<String>()
            user.bikes!!.map {
                bikeNamesList.add(it.value["bikeName"]!!)
            }

            view.setContent {
                SetUI(user, bikeNamesList)
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
private fun SetUI(user: User, bikeNamesList: ArrayList<String>) {

    Box(
        modifier = Modifier
            .background(colorResource(id = R.color.background_white_grey))
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Row() {
                val rowHeight = 128.dp
                Image(
                    painter = painterResource(R.drawable.default_profile_icon),
                    contentDescription = "userProfileImage",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(rowHeight)
                )

                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(rowHeight),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = user.userName!!,
                        fontSize = 20.sp
                    )
                    Text(
                        text = user.userEmail!!,
                        fontSize = 20.sp
                    )
                    Text(
                        text = bikeNamesList
                            .toString()
                            .replace('[', ' ')
                            .replace(']', ' '),
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}
