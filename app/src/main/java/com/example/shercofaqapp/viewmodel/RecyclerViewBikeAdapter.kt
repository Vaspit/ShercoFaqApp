package com.example.shercofaqapp.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.GarageItemBinding
import com.example.shercofaqapp.model.Bike
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RecyclerViewBikeAdapter : RecyclerView.Adapter<RecyclerViewBikeAdapter.ViewHolder>() {

    var bikeList = ArrayList<Bike>()

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val binding = GarageItemBinding.bind(item)

        fun bind(bike: Bike) = with(binding) {
            bikeNameTextView.text = bike.bikeName
            bikeModelYearTextView.text = bike.bikeModelYear
            bikeTypeTextView.text = bike.bikeType
            bikeEngineTypeTextView.text = bike.bikeEngineType
            bikeEngineVolumeTextView.text = bike.bikeEngineVolume
            bikeEditionTextView.text = bike.bikeEdition
            bikeImage.setImageResource(bike.bikeImage)

            //Garage item click
            itemView.setOnClickListener {

                val bundle = bundleOf(
                    "bikeId" to bike.bikeId
                )

                //Go to AddBikeFragment
                findNavController(itemView)
                    .navigate(R.id.action_garageFragment_to_workshopFragment, bundle)
            }

            editImageButton.setOnClickListener {
                showPopupMenu(itemView, bike)
            }
        }

        fun showPopupMenu(view: View, bike: Bike) {
            val popupMenu = PopupMenu(view.context, view)
            popupMenu.inflate(R.menu.popup_menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item.itemId) {
                    // Go to BikeFragment
                    R.id.actionPopupEdit -> {
                        val bundle = bundleOf(
                            "bikeId" to bike.bikeId,
                            "isUpdate" to true
                        )

                        findNavController(view)
                            .navigate(R.id.action_garageFragment_to_bikeFragment, bundle)
                    }
                }
                true
            })
            popupMenu.show()
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(item = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.garage_item, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(bikeList[position])
    }

    override fun getItemCount(): Int {
        return bikeList.size
    }

    fun addBikeList(bikeList: ArrayList<Bike>) {
        this.bikeList = bikeList
        notifyDataSetChanged()
    }

}

