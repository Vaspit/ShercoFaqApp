package com.example.shercofaqapp.view.workshop

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentPartsBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class PartsFragment : Fragment() {

    lateinit var binding: FragmentPartsBinding
    private lateinit var partsArray: HashMap<Any, Any> //test

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val database = Firebase.database.reference

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_parts, container, false)


        //---test------
        database.child("parts").get().addOnSuccessListener {
            Log.d("PARTS", it.toString())
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
        //-----------------------
        return binding.root

    }

}