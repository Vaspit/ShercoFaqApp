package com.example.shercofaqapp.view.workshop.faq

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentFaqBinding
import com.example.shercofaqapp.viewmodel.faq.FaqViewModel
import com.example.shercofaqapp.viewmodel.faq.RecyclerViewFaqAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class FaqFragment : Fragment() {

    lateinit var binding: FragmentFaqBinding
    private var currentBikeAddress = ""
    private val faqViewModel: FaqViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_faq, container, false)

        getOuterArguments(binding.root)

        CoroutineScope(Dispatchers.Main).launch {
            setRecyclerView()
        }

        return binding.root

    }

    private suspend  fun setRecyclerView() {

        withContext(Dispatchers.Main) {
            val issuesArrayList = faqViewModel.getIssues(currentBikeAddress)

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