package com.example.shercofaqapp.view.workshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentFaqBinding

class FaqFragment : Fragment() {

    lateinit var binding: FragmentFaqBinding

    companion object {
        fun newInstance() = FaqFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_faq, container, false)

        return binding.root

    }

}