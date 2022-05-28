package com.example.shercofaqapp.viewmodel.parts

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.PartTypeItemBinding
import com.example.shercofaqapp.model.PartType

class RecyclerViewPartsTypeAdapter(private val partTypeList: ArrayList<PartType>):
    RecyclerView.Adapter<RecyclerViewPartsTypeAdapter.ViewHolder>() {



    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = PartTypeItemBinding.bind(item)
        private val sharedPref: SharedPreferences = item.context
            .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        private val editor: SharedPreferences.Editor = sharedPref.edit()

        fun bind(partType: PartType) = with(binding) {
            partsTypeTextView.text = partType.partTypeName
            partsImageView.setImageResource(partType.partImage!!)

            itemView.setOnClickListener {
                editor.putString("partType", partType.partTypeName.toString().trim())
                editor.apply()
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_partsFragment_to_partsListFragment)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(item = LayoutInflater.from(parent.context)
                .inflate(R.layout.part_type_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(partTypeList[position])
    }

    override fun getItemCount(): Int {
        return partTypeList.size
    }

}