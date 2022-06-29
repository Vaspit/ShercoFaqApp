package com.example.shercofaqapp.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.GarageItemBinding
import com.example.shercofaqapp.model.Bike

class RecyclerViewBikeAdapter : RecyclerView.Adapter<RecyclerViewBikeAdapter.ViewHolder>() {

    private var bikeList = ArrayList<Bike>()

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val binding = GarageItemBinding.bind(item)

        fun bind(bike: Bike) = with(binding) {
            bikeNameTextView.text = bike.bikeName
            bikeModelYearTextView.text = bike.bikeModelYear
            bikeTypeTextView.text = bike.bikeType
            bikeEngineTypeTextView.text = bike.bikeEngineType
            bikeEngineVolumeTextView.text = bike.bikeEngineVolume
            bikeEditionTextView.text = bike.bikeEdition
            Glide.with(itemView)
                .load(bike.bikeImage)
                .placeholder(R.drawable.ic_baseline_pedal_bike_24)
                .error(R.drawable.ic_baseline_pedal_bike_24)
                .centerInside()
                .into(bikeImageView)

            //Garage item click
            itemView.setOnClickListener {

                val bundle = bundleOf(
                    "bikeName" to bike.bikeName,
                    "bikeModelYear" to bike.bikeModelYear,
                    "bikeType" to bike.bikeType,
                    "bikeEngineType" to bike.bikeEngineType,
                    "bikeEngineVolume" to bike.bikeEngineVolume,
                    "bikeEdition" to bike.bikeEdition,
                    "bikeImage" to bike.bikeImage.toString(),
                    "bikeFirebaseKey" to bike.bikeFirebaseKey
                )

                //Go to AddBikeFragment
                findNavController(itemView)
                    .navigate(R.id.action_garageFragment_to_workshopFragment, bundle)
            }

            editImageButton.setOnClickListener {
                showPopupMenu(itemView, bike)
            }
        }

        private fun showPopupMenu(view: View, bike: Bike) {
            val popupMenu = PopupMenu(view.context, view)
            popupMenu.inflate(R.menu.popup_menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item.itemId) {
                    // Go to BikeFragment
                    R.id.actionPopupEdit -> {
                        val bundle = bundleOf(
                            "isUpdate" to true,
                            "bikeName" to bike.bikeName,
                            "bikeModelYear" to bike.bikeModelYear,
                            "bikeType" to bike.bikeType,
                            "bikeEngineType" to bike.bikeEngineType,
                            "bikeEngineVolume" to bike.bikeEngineVolume,
                            "bikeEdition" to bike.bikeEdition,
                            "bikeImage" to bike.bikeImage.toString(),
                            "bikeFirebaseKey" to bike.bikeFirebaseKey
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

