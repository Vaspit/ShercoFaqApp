package com.example.shercofaqapp.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.GarageItemBinding
import com.example.shercofaqapp.model.Bike

class BikeAdapter : RecyclerView.Adapter<BikeAdapter.ViewHolder>() {

    private var bikeList = ArrayList<Bike>()

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val binding = GarageItemBinding.bind(item)

        fun bind(bike: Bike) = with(binding) {

            bikeNameTextView.text = bike.bikeName
            bikeModelYearTextView.text = bike.bikeModelYear
            bikeEngineTypeTextView.text = bike.bikeEngineType
            bikeEngineVolumeTextView.text = bike.bikeEngineVolume
            bikeEditionTextView.text = bike.bikeEdition
            bikeImage.setImageResource(bike.bikeImage)

        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        // Create a new view, which defines the UI of the list item
        return ViewHolder(item = LayoutInflater.from(viewGroup.context).inflate(R.layout.garage_item, viewGroup, false))

    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.bind(bikeList[position])

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {

        return bikeList.size

    }

    fun addBike(bike: Bike) {

        bikeList.add(bike)
        notifyDataSetChanged()

    }

    fun addBikeList(bikeList: ArrayList<Bike>) {

        this.bikeList = bikeList
        notifyDataSetChanged()

    }

}