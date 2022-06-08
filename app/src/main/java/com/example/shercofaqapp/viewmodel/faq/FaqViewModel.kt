package com.example.shercofaqapp.viewmodel.faq

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.model.repositories.FaqRepository
import com.example.shercofaqapp.model.Issue

class FaqViewModel: ViewModel() {

    fun getIssues(context: Context, bikeAddress: String): ArrayList<Issue> {
        Log.d("FAQ_VIEW_MODEL", FaqRepository(context).getIssues(bikeAddress).toString())
        return FaqRepository(context).getIssues(bikeAddress)
    }

}