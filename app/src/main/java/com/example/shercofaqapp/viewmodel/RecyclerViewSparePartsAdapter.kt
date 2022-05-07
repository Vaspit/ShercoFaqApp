package com.example.shercofaqapp.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.SparePartsItemBinding
import com.example.shercofaqapp.model.SparePart

class RecyclerViewSparePartsAdapter(private val sparePartsList: ArrayList<SparePart>):
    RecyclerView.Adapter<RecyclerViewSparePartsAdapter.ViewHolder>() {

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = SparePartsItemBinding.bind(item)
        private val sharedPref: SharedPreferences = item.context
            .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        private val editor: SharedPreferences.Editor = sharedPref.edit()

        fun bind(sparePart: SparePart) = with(binding) {
            sparePartsTextView.text = sparePart.sparePartName
            sparePartsImageView.setImageResource(sparePart.sparePartImage!!)

            itemView.setOnClickListener {
                editor.putString("currentSparePartName", sparePart.sparePartName.toString().trim())
                editor.putString("currentSparePartDescription", sparePart.sparePartDescription.toString().trim())
                editor.putString("currentSparePartLink", sparePart.sparePartLink.toString().trim())
                editor.putInt("currentSparePartImage", sparePart.sparePartImage)
                editor.apply()
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_sparePartsFragment_to_sparePartFragment)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(item = LayoutInflater.from(parent.context)
            .inflate(R.layout.spare_parts_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sparePartsList[position])
    }

    override fun getItemCount(): Int {
        return sparePartsList.size
    }

}