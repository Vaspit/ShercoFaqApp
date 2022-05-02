package com.example.shercofaqapp.view.workshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.shercofaqapp.R

class TorquesFragment : Fragment() {

    lateinit var binding: com.example.shercofaqapp.databinding.FragmentTorquesBinding

    companion object {
        fun newInstance() = TorquesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_torques, container, false)

        return binding.root

    }

}