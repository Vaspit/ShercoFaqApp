package com.example.shercofaqapp.model

import com.example.shercofaqapp.R

data class Torque (
    val torqueType: String,
    val torqueName: String,
    val torqueValue: String,
    val threadSize: String,
    val torqueImage: Int ?= R.drawable.ic_baseline_parts,
    val torqueNote: String
    )