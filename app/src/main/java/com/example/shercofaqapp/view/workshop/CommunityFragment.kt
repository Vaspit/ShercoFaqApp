package com.example.shercofaqapp.view.workshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentCommunityBinding

class CommunityFragment : Fragment() {

    lateinit var binding: FragmentCommunityBinding

    companion object {
        fun newInstance() = CommunityFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_community, container, false)

        return binding.root

    }

}