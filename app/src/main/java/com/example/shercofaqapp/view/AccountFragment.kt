package com.example.shercofaqapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.shercofaqapp.viewmodel.AccountViewModel

class AccountFragment : Fragment() {

    lateinit var accountViewModel: AccountViewModel
    private var userName = "UserName"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = ComposeView(requireContext())

        accountViewModel = ViewModelProvider(this)[AccountViewModel::class.java]
        accountViewModel.setUser()
        accountViewModel.userName.observe(viewLifecycleOwner, Observer {

            userName = it

            view.apply {
                setContent {
                    SetUI()
                }
            }

        })

        return view
    }

    @Composable
    private fun SetUI() {
        Text(text = userName)
    }

}