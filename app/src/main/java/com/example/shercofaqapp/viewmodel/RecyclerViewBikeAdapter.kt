package com.example.shercofaqapp.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.GarageItemBinding
import com.example.shercofaqapp.model.Bike

class RecyclerViewBikeAdapter : RecyclerView.Adapter<RecyclerViewBikeAdapter.ViewHolder>() {

    private var bikeList = ArrayList<Bike>()

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val binding = GarageItemBinding.bind(item)
        private val preferences: SharedPreferences = item.context
            .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        private val editor: SharedPreferences.Editor = preferences.edit()

        fun bind(bike: Bike) = with(binding) {

            bikeNameTextView.text = bike.bikeName
            bikeModelYearTextView.text = bike.bikeModelYear
            bikeEngineTypeTextView.text = bike.bikeEngineType
            bikeEngineVolumeTextView.text = bike.bikeEngineVolume
            bikeEditionTextView.text = bike.bikeEdition
            bikeImage.setImageResource(bike.bikeImage)

            itemView.setOnClickListener {

                editor.putBoolean("isUpdate", true)
                editor.commit()

                //Go to AddBikeFragment
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_garageFragment_to_bikeFragment)

            }

            editDeleteImageButton.setOnClickListener {

                //Log.d("onClick", "" + adapterPosition)
                showPopupMenu(itemView)

            }

        }

        fun showPopupMenu(view: View) {

            val popupMenu = PopupMenu(view.context, view)
            popupMenu.inflate(R.menu.popup_menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item.itemId) {

                    // Go to BikeFragment
                    R.id.actionPopupEdit -> {

                        //Put adapter position for the transmitting to corresponding BikeFragment
                        editor.putInt("bikeAdapterPosition", adapterPosition)
                        editor.commit()
                        //Log.d("bikeAdapterPosition", "" + preferences.getInt("bikeAdapterPosition", -1))
                        Navigation.findNavController(view)
                            .navigate(R.id.action_garageFragment_to_bikeFragment)
                    }

                    //Delete bike
                    R.id.actionPopupDelete ->
                        Toast.makeText(view.context, "You Clicked : " + item.title + " " + adapterPosition, Toast.LENGTH_SHORT).show()

                }
                true
            })
            popupMenu.show()

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

