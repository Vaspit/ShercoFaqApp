package com.example.shercofaqapp.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.GarageItemBinding
import com.example.shercofaqapp.model.Bike

class RecyclerViewBikeAdapter : RecyclerView.Adapter<RecyclerViewBikeAdapter.ViewHolder>() {

    var bikeList = ArrayList<Bike>()

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val binding = GarageItemBinding.bind(item)
        private val sharedPref: SharedPreferences = item.context
            .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        private val editor: SharedPreferences.Editor = sharedPref.edit()

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

                editor.putLong("bikeId", bike.bikeId)
                editor.commit()
                //Go to AddBikeFragment
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_garageFragment_to_workshopFragment)

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

                        //Put adapter position for the transmitting to corresponding BikeFragment
                        editor.putBoolean("isUpdate", true)
                        editor.putLong("bikeId", bike.bikeId)
                        editor.commit()

                        Navigation.findNavController(view)
                            .navigate(R.id.action_garageFragment_to_bikeFragment)

                    }

                }
                true
            })
            popupMenu.show()

        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        // Create a new view, which defines the UI of the list item
        return ViewHolder(item = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.garage_item, viewGroup, false))

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

    fun deleteBike(bikeList: ArrayList<Bike>, position: Int) {

        this.bikeList = bikeList
        bikeList.remove(bikeList[position])
        notifyDataSetChanged()

    }

}

