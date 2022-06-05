package com.example.shercofaqapp.viewmodel.parts

import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.R
import com.example.shercofaqapp.model.PartType
import java.util.ArrayList

class PartTypeViewModel : ViewModel() {

    fun getPartTypeArray(): ArrayList<PartType> {

        return arrayListOf(
            PartType("Fork", R.drawable.fork),
            PartType("Handlebar", R.drawable.handlebar),
            PartType("Frame", R.drawable.frame),
            PartType("Shock and arm", R.drawable.shockarm),
            PartType("Exhaust", R.drawable.exhaust),
            PartType("Filter", R.drawable.filter),
            PartType("Front wheel", R.drawable.frontwheel),
            PartType("Rear wheel", R.drawable.rearwheel),
            PartType("Brakes", R.drawable.brakes),
            PartType("Seat", R.drawable.seat),
            PartType("Electric", R.drawable.electric),
            PartType("Plastic", R.drawable.plastic),
            PartType("Case", R.drawable.casecase),
            PartType("Carburetor", R.drawable.carburetor),
            PartType("Clutch", R.drawable.clutch),
            PartType("Cooling", R.drawable.cooling),
            PartType("Ignition", R.drawable.ignition),
            PartType("Gearbox", R.drawable.gearbox),
            PartType("Protection", R.drawable.ic_protection)
        )

    }

}