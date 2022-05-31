package com.example.shercofaqapp.viewmodel.faq

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shercofaqapp.model.FaqRepository
import com.example.shercofaqapp.model.Issue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FaqViewModel(@SuppressLint("StaticFieldLeak") private val context: Context): ViewModel() {

    fun getIssues(bikeAddress: String): ArrayList<Issue> {
        Log.d("FAQ_VIEW_MODEL", FaqRepository(context).getIssues(bikeAddress).toString())
        return FaqRepository(context).getIssues(bikeAddress)
    }

}