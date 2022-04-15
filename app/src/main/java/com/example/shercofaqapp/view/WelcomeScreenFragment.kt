package com.example.shercofaqapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentWelcomeScreenBinding
import com.google.android.material.navigation.NavigationBarItemView

class WelcomeScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentWelcomeScreenBinding =
            DataBindingUtil.inflate(
                inflater, R.layout.fragment_welcome_screen, container, false
            )

        binding.startButton.setOnClickListener { view:View ->

            Navigation.findNavController(view)
                .navigate(R.id.action_welcomeScreenFragment2_to_garageFragment)

        }

        return binding.root
    }

}