package com.example.shercofaqapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentGarageBinding
import com.example.shercofaqapp.databinding.FragmentWorkSpaceBinding

class GarageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGarageBinding =
            DataBindingUtil.inflate(
                inflater, R.layout.fragment_garage, container, false)



        return binding.root
    }

}