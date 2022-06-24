package com.example.shercofaqapp.view.workshop.faq

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentFaqBinding
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.model.Issue
import com.example.shercofaqapp.model.Torque
import com.example.shercofaqapp.utils.CurrentBikeAddress
import com.example.shercofaqapp.viewmodel.GarageFragmentViewModel
import com.example.shercofaqapp.viewmodel.faq.FaqViewModel
import com.example.shercofaqapp.viewmodel.faq.RecyclerViewFaqAdapter
import com.example.shercofaqapp.viewmodel.torques.RecyclerViewTorquesAdapter
import com.example.shercofaqapp.viewmodel.torques.TorquesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FaqFragment : Fragment() {

    lateinit var binding: FragmentFaqBinding
    private var currentBikeAddress = ""
    private lateinit var faqViewModel: FaqViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_faq, container, false)

        getOuterArguments(binding.root)

        faqViewModel = ViewModelProvider(this)[FaqViewModel::class.java]

        CoroutineScope(Dispatchers.IO).launch {
            setRecyclerView()
        }

        return binding.root

    }

    private suspend  fun setRecyclerView() {

        val issuesArrayList = faqViewModel.getIssues(requireContext(), currentBikeAddress)

        withContext(Dispatchers.Main) {
            binding.faqRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.faqRecyclerView.adapter = RecyclerViewFaqAdapter(issuesArrayList)
            binding.faqRecyclerView.setHasFixedSize(true)
        }
    }

    private fun getOuterArguments(view: View) {
        val sharedPref = view.context
            .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        currentBikeAddress = sharedPref.getString("currentBikeAddress", "").toString()
    }

}