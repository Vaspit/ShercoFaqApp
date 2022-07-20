package com.example.shercofaqapp.view.workshop.torques

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentTorquesBinding
import com.example.shercofaqapp.viewmodel.torques.RecyclerViewTorquesAdapter
import com.example.shercofaqapp.viewmodel.torques.TorquesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class TorquesFragment : Fragment() {

    private var currentBikeAddress = ""
    private val torquesViewModel: TorquesViewModel by viewModels()
    lateinit var binding: FragmentTorquesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_torques, container, false)

        getOuterArguments(binding.root)

        CoroutineScope(Dispatchers.Main).launch {
            setRecyclerViews()
        }

        return binding.root
    }

    private suspend  fun setRecyclerViews() {

        withContext(Dispatchers.Main) {
            val engineTorques = torquesViewModel.getEngineTorques(currentBikeAddress)
            val chassisTorques = torquesViewModel.getChassisTorques(currentBikeAddress)

            binding.engineRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.engineRecyclerView.adapter = RecyclerViewTorquesAdapter(engineTorques)
            binding.engineRecyclerView.setHasFixedSize(true)

            binding.chassisRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.chassisRecyclerView.adapter = RecyclerViewTorquesAdapter(chassisTorques)
            binding.chassisRecyclerView.setHasFixedSize(true)
        }
    }

    private fun getOuterArguments(view: View) {
        val sharedPref = view.context
            .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        currentBikeAddress = sharedPref.getString("currentBikeAddress", "").toString()
    }
}