package com.example.shercofaqapp.model

import com.example.shercofaqapp.R

data class SparePart(
    val sparePartName: String ?= null,
    val sparePartDescription: String ?= null,
    val sparePartLink: String ?= null,
    var sparePartImage: Int ?= R.drawable.ic_baseline_parts,
    val sparePartFor: ArrayList<String> ?= null
)