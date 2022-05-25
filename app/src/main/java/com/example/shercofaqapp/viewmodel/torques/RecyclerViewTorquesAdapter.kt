package com.example.shercofaqapp.viewmodel.torques

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.TorqueItemBinding
import com.example.shercofaqapp.model.Torque

class RecyclerViewTorquesAdapter(private val torquesArrayList: List<Torque>):
    RecyclerView.Adapter<RecyclerViewTorquesAdapter.ViewHolder>(){

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val binding = TorqueItemBinding.bind(item)
//        private val sharedPref: SharedPreferences = item.context
//            .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
//        private val editor: SharedPreferences.Editor = sharedPref.edit()

        fun bind(torque: Torque) = with(binding) {
            torqueName.text = torque.torqueName

            itemView.setOnClickListener {
//                editor.putString("currentSparePartsType", part.partType)
//                editor.putString("currentSparePartsName", part.partName)
//                editor.apply()
//                Navigation.findNavController(itemView)
//                    .navigate(R.id.action_partsListFragment_to_sparePartsFragment)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return RecyclerViewTorquesAdapter.ViewHolder(
            item = LayoutInflater.from(parent.context)
                .inflate(R.layout.torque_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(torquesArrayList[position])
    }

    override fun getItemCount(): Int {
        return  torquesArrayList.size
    }

}