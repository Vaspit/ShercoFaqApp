package com.example.shercofaqapp.view.workshop.parts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentSparePartBinding
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.model.SparePart

class SparePartFragment : Fragment() {

    lateinit var binding: FragmentSparePartBinding
    lateinit var bike: Bike
    private lateinit var currentSparePartDescription: String
    private lateinit var currentSparePartName: String
    private lateinit var currentSparePartLink: String
    private lateinit var currentSparePartImage: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        currentSparePartDescription = arguments?.getString("currentSparePartDescription").toString()
        currentSparePartName = arguments?.getString("currentSparePartName").toString()
        currentSparePartLink = arguments?.getString("currentSparePartLink").toString()
        currentSparePartImage = arguments?.getString("currentSparePartImage")!!

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_spare_part, container, false)
        Glide.with(requireContext())
            .load(currentSparePartImage)
            .placeholder(R.drawable.ic_baseline_parts)
            .error(R.drawable.ic_baseline_parts)
            .centerInside()
            .into(binding.sparePartImageView)
        binding.sparePartDescriptionTextView.text = currentSparePartDescription
        binding.sparePartNameTextView.text = currentSparePartName
        binding.buyButton.setOnClickListener { onBuy(currentSparePartLink, currentSparePartName) }

        return binding.root
    }

    private fun gotToUrl(currentSparePartLink: String) {
        val uri = Uri.parse(currentSparePartLink)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun onBuy(currentSparePartLink: String, currentSparePartName: String) {

        if (currentSparePartLink != "") {
            gotToUrl(currentSparePartLink)
        } else {
            gotToUrl("https://www.google.com/search?q={$currentSparePartName}")
        }
    }
}