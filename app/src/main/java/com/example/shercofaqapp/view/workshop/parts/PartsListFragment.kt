package com.example.shercofaqapp.view.workshop.parts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentPartsListBinding
import com.example.shercofaqapp.model.Part
import com.example.shercofaqapp.viewmodel.parts.PartsListViewModel
import com.example.shercofaqapp.viewmodel.parts.RecyclerViewPartsListAdapter
import java.util.ArrayList

class PartsListFragment : Fragment() {

    lateinit var binding: FragmentPartsListBinding
    lateinit var viewModel: PartsListViewModel
    lateinit var partsListArrayList: ArrayList<Part>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val partType = arguments?.getString("currentPartType")

        viewModel = ViewModelProvider(this)[PartsListViewModel::class.java]
        partsListArrayList = viewModel.getPartsListArrayList(partType!!)

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_parts_list, container, false)
        binding.apply {
            if (partsListArrayList.size != 0) {
                partsListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                partsListRecyclerView.adapter = RecyclerViewPartsListAdapter(partsListArrayList)
                partsListRecyclerView.setHasFixedSize(true)
            } else {
                Toast.makeText(context,
                    "There are no known spare parts yet!", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root

    }

}