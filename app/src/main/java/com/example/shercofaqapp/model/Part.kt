package com.example.shercofaqapp.model

import com.example.shercofaqapp.R

data class Part (
    val partType: String ?= null,
    val partName: String ?= null,
    val partImage: Int ?= R.drawable.ic_baseline_parts
        )