package com.example.shercofaqapp.view.workshop.faq

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentFaqBinding
import com.example.shercofaqapp.databinding.FragmentSolutionBinding
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.utils.CurrentBikeAddress
import com.example.shercofaqapp.viewmodel.GarageFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SolutionFragment : Fragment() {

    lateinit var binding: FragmentSolutionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_solution, container, false)

        return binding.root
    }

}