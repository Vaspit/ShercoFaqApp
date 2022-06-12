package com.example.shercofaqapp.view.workshop.parts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentPartsTypeBinding
import com.example.shercofaqapp.viewmodel.parts.RecyclerViewPartsTypeAdapter
import com.example.shercofaqapp.viewmodel.parts.PartTypeViewModel


class PartsTypeFragment : Fragment() {

    lateinit var binding: FragmentPartsTypeBinding
    lateinit var viewModel: PartTypeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_parts_type, container, false)
        viewModel = ViewModelProvider(this)[PartTypeViewModel::class.java]

        binding.apply {

            val recyclerViewAdapter = RecyclerViewPartsTypeAdapter(viewModel.getPartTypeArray())

            partsTypeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            partsTypeRecyclerView.adapter = recyclerViewAdapter
            partsTypeRecyclerView.setHasFixedSize(true)
        }

        return binding.root

    }
}