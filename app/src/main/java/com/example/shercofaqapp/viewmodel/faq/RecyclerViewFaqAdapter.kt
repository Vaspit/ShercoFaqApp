package com.example.shercofaqapp.viewmodel.faq

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FaqItemBinding
import com.example.shercofaqapp.model.Issue

class RecyclerViewFaqAdapter(private val issuesArrayList: ArrayList<Issue>):
    RecyclerView.Adapter<RecyclerViewFaqAdapter.ViewHolder>() {

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val binding = FaqItemBinding.bind(item)

        fun bind(issue: Issue) = with(binding) {
            faqItemTextView.text = issue.issueDescription

            itemView.setOnClickListener {
                val bundle = bundleOf(
                    "currentIssueDescription" to issue.issueDescription,
                    "currentIssueSolutions" to issue.issueSolutions,
                )
//                Navigation.findNavController(itemView)
//                    .navigate(R.id.action_faqFragment_to_solutionsListFragment, bundle)
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_faqFragment_to_solutionListComposeFragment, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            item = LayoutInflater.from(parent.context)
                .inflate(R.layout.faq_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(issuesArrayList[position])
    }

    override fun getItemCount(): Int {
        return  issuesArrayList.size
    }

}