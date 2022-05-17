package com.example.shercofaqapp.view.workshop.parts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentPartsTypeBinding
import com.example.shercofaqapp.model.PartType
import com.example.shercofaqapp.viewmodel.parts.RecyclerViewPartsTypeAdapter
import java.util.ArrayList


class PartsTypeFragment : Fragment() {

    lateinit var binding: FragmentPartsTypeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_parts_type, container, false)
        binding.apply {

            val recyclerViewAdapter = RecyclerViewPartsTypeAdapter(setPartTypeUI())

            partsTypeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            partsTypeRecyclerView.adapter = recyclerViewAdapter
            partsTypeRecyclerView.setHasFixedSize(true)
        }

        return binding.root

    }

    private fun setPartTypeUI(): ArrayList<PartType> {

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
            PartType("Gearbox", R.drawable.gearbox)
        )

    }


}