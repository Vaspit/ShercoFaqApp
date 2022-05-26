package com.example.shercofaqapp.viewmodel.torques

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.TorqueItemBinding
import com.example.shercofaqapp.model.Torque

class RecyclerViewTorquesAdapter(private val torquesArrayList: List<Torque>):
    RecyclerView.Adapter<RecyclerViewTorquesAdapter.ViewHolder>(){

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val binding = TorqueItemBinding.bind(item)

        fun bind(torque: Torque) = with(binding) {
            torqueName.text = torque.torqueName

            itemView.setOnClickListener {
                val bundle = bundleOf(
                    "currentTorqueName" to torque.torqueName,
                    "currentTorqueValue" to torque.torqueValue,
                    "currentThreadSize" to torque.threadSize,
                    "currentTorqueImage" to torque.torqueImage,
                    "currentTorqueNote" to torque.torqueNote
                )
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_torquesFragment_to_torqueFragment, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
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