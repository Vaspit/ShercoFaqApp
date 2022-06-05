package com.example.shercofaqapp.viewmodel.parts

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.PartListItemBinding
import com.example.shercofaqapp.model.Part

class RecyclerViewPartsListAdapter(private val partListList: ArrayList<Part>):
    RecyclerView.Adapter<RecyclerViewPartsListAdapter.ViewHolder>() {

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val binding = PartListItemBinding.bind(item)

        fun bind(part: Part) = with(binding) {
            partsNameTextView.text = part.partName
            partsImageView.setImageResource(part.partImage!!)

            itemView.setOnClickListener {
                val bundle = bundleOf(
                    "currentSparePartsType" to part.partType,
                    "currentSparePartsName" to part.partName
                )
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_partsListFragment_to_sparePartsFragment, bundle)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(item = LayoutInflater.from(parent.context)
            .inflate(R.layout.part_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(partListList[position])
    }

    override fun getItemCount(): Int {
        return partListList.size
    }

}