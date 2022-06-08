package com.example.shercofaqapp.viewmodel.parts

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.SparePartsItemBinding
import com.example.shercofaqapp.model.SparePart
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class RecyclerViewSparePartsAdapter(
    options: FirebaseRecyclerOptions<SparePart>
    ) : FirebaseRecyclerAdapter<SparePart, RecyclerViewSparePartsAdapter.SparePartHolder>(options){

    class SparePartHolder(item: View): RecyclerView.ViewHolder(item) {

        val binding = SparePartsItemBinding.bind(item)
        val sparePartName: TextView = item.findViewById(R.id.sparePartsTextView)
        val sparePartImage: ImageView = item.findViewById(R.id.sparePartsImageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SparePartHolder {
        val item = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.spare_parts_item, parent, false)

        return SparePartHolder(item)
    }

    override fun onBindViewHolder(holder: SparePartHolder, position: Int, model: SparePart) {

        holder.sparePartName.text = model.sparePartName.toString().trim()

        //the check that the spare parts image exists
        if (model.sparePartImage == 0) {
            model.sparePartImage = R.drawable.ic_baseline_parts
        }
        holder.sparePartImage.setImageResource(model.sparePartImage!!)
        holder.itemView.setOnClickListener {

            val bundle = bundleOf(
                "currentSparePartName" to model.sparePartName,
                "currentSparePartDescription" to model.sparePartDescription,
                "currentSparePartLink" to model.sparePartLink,
                "currentSparePartImage" to model.sparePartImage
            )

            Navigation.findNavController(holder.itemView)
                .navigate(R.id.action_sparePartsFragment_to_sparePartFragment, bundle)
        }
    }
}