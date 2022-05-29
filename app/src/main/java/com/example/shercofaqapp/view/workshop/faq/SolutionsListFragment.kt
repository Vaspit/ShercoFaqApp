package com.example.shercofaqapp.view.workshop.faq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shercofaqapp.R
import com.example.shercofaqapp.model.Solution
import com.example.shercofaqapp.viewmodel.faq.RecyclerViewSolutionsListAdapter

class SolutionsListFragment : Fragment() {

    lateinit var binding: com.example.shercofaqapp.databinding.FragmentSolutionsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val solutionTitle = arguments?.getString("currentIssueDescription")
        val solutionsArrayList = arguments?.get("currentIssueSolutions") as ArrayList<Solution>

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_solutions_list, container, false)

        binding.solutionTitleTextView.text = solutionTitle
        setRecyclerView(solutionsArrayList)

        return binding.root
    }

    private fun setRecyclerView(solutionsArrayList: ArrayList<Solution>) {
        binding.solutionsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.solutionsRecyclerView.adapter = RecyclerViewSolutionsListAdapter(solutionsArrayList)
        binding.solutionsRecyclerView.setHasFixedSize(true)
    }

}