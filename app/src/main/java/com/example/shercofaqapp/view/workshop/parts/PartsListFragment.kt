package com.example.shercofaqapp.view.workshop.parts

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentPartsListBinding
import com.example.shercofaqapp.model.Part
import com.example.shercofaqapp.viewmodel.parts.RecyclerViewPartsListAdapter
import java.util.ArrayList

class PartsListFragment : Fragment() {

    lateinit var binding: FragmentPartsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val sharedPref: SharedPreferences = requireActivity()
            .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val partType = sharedPref.getString("partType", "")

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_parts_list, container, false)
        binding.apply {
            if (setPartListUI(partType!!).size != 0) {
                partsListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                partsListRecyclerView.adapter = RecyclerViewPartsListAdapter(setPartListUI(partType))
                partsListRecyclerView.setHasFixedSize(true)
            } else {
                Toast.makeText(context,
                    "There are no known spare parts yet!", Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }

    private fun setPartListUI(partType: String): ArrayList<Part> {
        val filteredArrayList: ArrayList<Part> = arrayListOf()
        val arrayList = arrayListOf(
            Part("Fork", "Steering column bearing", R.drawable.ic_baseline_parts),
            Part("Handlebar", "Handlebar", R.drawable.ic_baseline_parts),
            Part("Frame", "Foot pegs", R.drawable.ic_baseline_parts),
            Part("Shock and arm", "Chain guide", R.drawable.ic_baseline_parts),
            Part("Shock and arm", "Delta link bearings", R.drawable.ic_baseline_parts),
            Part("Shock and arm", "H-link bearings", R.drawable.ic_baseline_parts),
            Part("Shock and arm", "Swing arm bearings", R.drawable.ic_baseline_parts),
            Part("Shock and arm", "H-link washers", R.drawable.ic_baseline_parts),
            Part("Shock and arm", "Swing arm washers", R.drawable.ic_baseline_parts),
            Part("Shock and arm", "Delta link sleeves", R.drawable.ic_baseline_parts),
            Part("Shock and arm", "Swing arm sleeves", R.drawable.ic_baseline_parts),
            Part("Front wheel", "Wheel bearings", R.drawable.ic_baseline_parts),
            Part("Front wheel", "Oil seals", R.drawable.ic_baseline_parts),
            Part("Rear wheel", "Wheel bearings", R.drawable.ic_baseline_parts),
            Part("Rear wheel", "Oil seals", R.drawable.ic_baseline_parts),
            Part("Rear wheel", "Sprocket Z49", R.drawable.ic_baseline_parts),
            Part("Brakes", "Front brake pads", R.drawable.ic_baseline_parts),
            Part("Brakes", "Rear brake pads", R.drawable.ic_baseline_parts),
            Part("Brakes", "Brake pedal bearings", R.drawable.ic_baseline_parts),
            Part("Brakes", "Brake pedal bearings", R.drawable.ic_baseline_parts),
            Part("Plastic", "Plastic kit", R.drawable.ic_baseline_parts),
            Part("Case", "Cylinder head seal", R.drawable.ic_baseline_parts),
            Part("Case", "Crankshaft oil seal", R.drawable.ic_baseline_parts),
            Part("Case", "Drive sprocket oil seal", R.drawable.ic_baseline_parts),
            Part("Case", "Connecting rod", R.drawable.ic_baseline_parts),
            Part("Case", "Crankshaft bearings", R.drawable.ic_baseline_parts),
            Part("Carburetor", "Carburetor", R.drawable.ic_baseline_parts),
            Part("Clutch", "Friction plates", R.drawable.ic_baseline_parts),
            Part("Clutch", "Clutch basket inserts", R.drawable.ic_baseline_parts),
            Part("Cooling", "Pump oil seal", R.drawable.ic_baseline_parts),
            Part("Ignition", "Starter", R.drawable.ic_baseline_parts),
            Part("Ignition", "Starter brushes", R.drawable.ic_baseline_parts),
            Part("Gearbox", "Gearbox lever bolt", R.drawable.ic_baseline_parts)
        )

        //Fill out the array by corresponding parts type
        for (index in arrayList.indices) {
            if (arrayList[index].partType.toString().trim() == partType) {
                filteredArrayList.add(arrayList[index])
            }
        }
        return filteredArrayList
    }


}