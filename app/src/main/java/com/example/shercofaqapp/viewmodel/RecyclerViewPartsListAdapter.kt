package com.example.shercofaqapp.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.PartListItemBinding
import com.example.shercofaqapp.databinding.PartTypeItemBinding
import com.example.shercofaqapp.model.Part
import com.example.shercofaqapp.model.PartType

class RecyclerViewPartsListAdapter(private val partListList: ArrayList<Part>):
    RecyclerView.Adapter<RecyclerViewPartsListAdapter.ViewHolder>() {

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val binding = PartListItemBinding.bind(item)
        private val sharedPref: SharedPreferences = item.context
            .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        private val editor: SharedPreferences.Editor = sharedPref.edit()

        fun bind(part: Part) = with(binding) {
            partsNameTextView.text = part.partName
            partsImageView.setImageResource(part.partImage!!)

            itemView.setOnClickListener {
                editor.putString("currentSparePartsType", part.partType)
                editor.putString("currentSparePartsName", part.partName)
                editor.apply()
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_partsListFragment_to_sparePartsFragment)
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