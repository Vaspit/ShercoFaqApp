package com.example.shercofaqapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.shercofaqapp.R
import com.example.shercofaqapp.model.User
import com.example.shercofaqapp.viewmodel.AccountViewModel

class AccountFragment : Fragment() {

    lateinit var accountViewModel: AccountViewModel
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = ComposeView(requireContext())
        user = User(
            "UserName",
            "UserEmail",
            R.drawable.default_profile_icon.toLong(),
            listOf("First bike")
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
            user.userProfileImage = it
        })
        accountViewModel.bikeList.observe(viewLifecycleOwner, Observer {
            user.bikes = it

            view.apply {
                setContent {
                    SetUI(user)
                }
            }
        })

        return view
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
            Image(
                painter = painterResource(user.userProfileImage.toInt()),
                contentDescription = "userProfileImage",
                modifier = Modifier.padding(8.dp).size(128.dp)
            )

            Column() {
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