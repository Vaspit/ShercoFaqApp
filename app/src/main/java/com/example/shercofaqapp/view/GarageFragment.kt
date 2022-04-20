package com.example.shercofaqapp.view

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
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentGarageBinding
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.viewmodel.RecyclerViewBikeAdapter
import com.example.shercofaqapp.viewmodel.GarageFragmentViewModel


class GarageFragment : Fragment() {

    lateinit var binding: FragmentGarageBinding
    private val recyclerViewAdapter = RecyclerViewBikeAdapter()
    private val model: GarageFragmentViewModel by viewModels()
    private var bikeArrayList: ArrayList<Bike> = ArrayList()
    lateinit var editor: SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val sharedPref = requireActivity()
            .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        editor = sharedPref.edit()

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_garage, container, false)
        initialization()


        return binding.root

    }

    private fun initialization() {

        binding.apply {

            //loadBikesInArray()

            garageRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            garageRecyclerView.adapter = recyclerViewAdapter
            garageRecyclerView.setHasFixedSize(true)

            model.bikes.observe(viewLifecycleOwner,
                Observer<List<Any?>> { bikes ->

                    bikeArrayList = bikes as kotlin.collections.ArrayList<Bike>
                    recyclerViewAdapter.addBikeList(bikeArrayList)
                    recyclerViewAdapter.notifyDataSetChanged()
                })

            floatingActionButton.setOnClickListener {

                editor.putBoolean("isUpdate", false)
                editor.commit()

                //Go to AddBikeFragment
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_garageFragment_to_bikeFragment)

            }

        }

    }

}