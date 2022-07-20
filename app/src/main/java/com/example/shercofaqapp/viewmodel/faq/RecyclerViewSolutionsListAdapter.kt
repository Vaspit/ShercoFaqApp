package com.example.shercofaqapp.viewmodel.faq

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.SolutionListItemBinding
import com.example.shercofaqapp.model.Solution

class RecyclerViewSolutionsListAdapter(private val solutionsArrayList: ArrayList<Solution>):
    RecyclerView.Adapter<RecyclerViewSolutionsListAdapter.ViewHolder>() {

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val binding = SolutionListItemBinding.bind(item)

        fun bind(solution: Solution) = with(binding) {
            solutionTextView.text = solution.solutionText
            if (solution.solutionImage != 0) {
                solutionsListImageView.visibility = View.VISIBLE
                solutionsListImageView.setImageResource(solution.solutionImage!!)
            } else {
                solutionsListImageView.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        return ViewHolder(
            item = LayoutInflater.from(parent.context)
                .inflate(R.layout.solution_list_item, parent, false)
        )

    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(solutionsArrayList[position])
    }

    override fun getItemCount(): Int {
        return solutionsArrayList.size
    }
}