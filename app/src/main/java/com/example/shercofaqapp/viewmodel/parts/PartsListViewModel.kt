package com.example.shercofaqapp.viewmodel.parts

import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.R
import com.example.shercofaqapp.model.Part
import java.util.ArrayList

class PartsListViewModel : ViewModel() {

    fun getPartsListArrayList(partType: String): ArrayList<Part> {
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
            Part("Cooling", "Radiator cooler", R.drawable.ic_baseline_parts),
            Part("Ignition", "Starter", R.drawable.ic_baseline_parts),
            Part("Ignition", "Starter brushes", R.drawable.ic_baseline_parts),
            Part("Electric", "Battery", R.drawable.ic_baseline_parts),
            Part("Gearbox", "Gearbox lever bolt", R.drawable.ic_baseline_parts),
            Part("Protection", "Clutch cover", R.drawable.ic_baseline_parts),
            Part("Protection", "Ignition cover", R.drawable.ic_baseline_parts)
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