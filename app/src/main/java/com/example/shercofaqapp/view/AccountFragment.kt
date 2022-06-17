package com.example.shercofaqapp.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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
import com.example.shercofaqapp.viewmodel.AccountViewModel
import com.google.firebase.auth.FirebaseAuth

class AccountFragment : Fragment() {

    lateinit var editor: SharedPreferences.Editor
    lateinit var accountViewModel: AccountViewModel
    private lateinit var user: User

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
        user = User(
            "UserName",
            "UserEmail",
            "",
            hashMapOf()
        )

        accountViewModel = ViewModelProvider(this)[AccountViewModel::class.java]
        accountViewModel.setUser()
        accountViewModel.userName.observe(viewLifecycleOwner, Observer {
            user.userName = it
        })
        accountViewModel.userEmail.observe(viewLifecycleOwner, Observer {
            user.userEmail = it
        })
        accountViewModel.userProfileImage.observe(viewLifecycleOwner, Observer {
            user.userProfileImageUrl = it
        })
        accountViewModel.bikeList.observe(viewLifecycleOwner, Observer {
            user.bikes = it

            Log.d("BIKES_HASH_MAP", it.toString())

            view.apply {
                setContent {
                    SetUI(user)
                }
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
private fun SetUI(user: User) {

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
                        text = user.userName,
                        fontSize = 20.sp
                    )
                    Text(
                        text = user.userEmail,
                        fontSize = 20.sp
                    )
                    Text(
                        text = user.bikes.toString(),
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}
