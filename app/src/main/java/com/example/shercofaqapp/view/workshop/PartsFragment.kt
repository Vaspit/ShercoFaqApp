package com.example.shercofaqapp.view.workshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentPartsBinding
import com.example.shercofaqapp.model.PartType
import com.example.shercofaqapp.viewmodel.RecyclerViewPartsAdapter
import java.util.ArrayList


class PartsFragment : Fragment() {

    lateinit var binding: FragmentPartsBinding
//    private lateinit var partsArray: HashMap<Any, Any> //test


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_parts, container, false)
        binding.apply {

            val recyclerViewAdapter = RecyclerViewPartsAdapter(setPartTypeUI())

            partsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            partsRecyclerView.adapter = recyclerViewAdapter
            partsRecyclerView.setHasFixedSize(true)
        }

        //---test------
//        database.child("parts").get().addOnSuccessListener {
//            Log.d("PARTS", it.value.toString())
//        }.addOnFailureListener{
//            Log.e("firebase", "Error getting data", it)
//        }
        //-----------------------
        return binding.root

    }

    private fun setPartTypeUI(): ArrayList<PartType> {

        return arrayListOf(
            PartType("Fork", R.drawable.ic_baseline_parts),
            PartType("Handlebar", R.drawable.ic_baseline_parts),
            PartType("Frame", R.drawable.ic_baseline_parts),
            PartType("Shock, Arm", R.drawable.ic_baseline_parts),
            PartType("Exhaust", R.drawable.ic_baseline_parts),
            PartType("Filter", R.drawable.ic_baseline_parts),
            PartType("Front Wheel", R.drawable.ic_baseline_parts),
            PartType("Rear Wheel", R.drawable.ic_baseline_parts),
            PartType("Brakes", R.drawable.ic_baseline_parts),
            PartType("Seat", R.drawable.ic_baseline_parts),
            PartType("Electric", R.drawable.ic_baseline_parts),
            PartType("Plastic", R.drawable.ic_baseline_parts),
            PartType("Case", R.drawable.ic_baseline_parts),
            PartType("Carburetor", R.drawable.ic_baseline_parts),
            PartType("Clutch", R.drawable.ic_baseline_parts),
            PartType("Cooling", R.drawable.ic_baseline_parts),
            PartType("Ignition", R.drawable.ic_baseline_parts),
            PartType("Gearbox", R.drawable.ic_baseline_parts)
        )

    }


}