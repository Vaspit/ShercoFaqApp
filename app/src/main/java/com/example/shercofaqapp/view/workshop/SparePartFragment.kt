package com.example.shercofaqapp.view.workshop

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentSparePartBinding


class SparePartFragment : Fragment() {

    lateinit var binding: FragmentSparePartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_spare_part, container, false)

        return binding.root
    }
}