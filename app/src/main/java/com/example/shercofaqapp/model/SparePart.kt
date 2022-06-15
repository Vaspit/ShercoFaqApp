package com.example.shercofaqapp.model

data class SparePart(
    val sparePartName: String ?= null,
    val sparePartDescription: String ?= null,
    val sparePartLink: String ?= null,
    var sparePartImage: String ?= "",
    val sparePartFor: ArrayList<String> ?= null
)