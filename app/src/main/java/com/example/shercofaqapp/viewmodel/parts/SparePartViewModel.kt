package com.example.shercofaqapp.viewmodel.parts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.model.SparePart

class SparePartViewModel(sparePart: SparePart) : ViewModel() {

    private val _sparePart = MutableLiveData<SparePart>()
    val sparePart : LiveData<SparePart>
        get() = _sparePart

}