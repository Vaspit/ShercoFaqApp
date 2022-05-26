package com.example.shercofaqapp.view.workshop.torques

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentTorqueBinding

class TorqueFragment : Fragment() {

    private lateinit var binding: FragmentTorqueBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val torqueName = arguments?.getString("currentTorqueName")


        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_torque, container, false)
        binding.torqueNameTextView.text = torqueName

        return binding.root
    }

}