package com.example.shercofaqapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentPartsBinding

class PartsFragment : Fragment() {

    lateinit var binding: FragmentPartsBinding

    companion object {
        fun newInstance() = PartsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_parts, container, false)

        return binding.root

    }

}