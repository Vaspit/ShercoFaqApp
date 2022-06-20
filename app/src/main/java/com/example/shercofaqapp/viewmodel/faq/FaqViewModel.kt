package com.example.shercofaqapp.viewmodel.faq

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.model.repositories.FaqRepository
import com.example.shercofaqapp.model.Issue

class FaqViewModel: ViewModel() {

    fun getIssues(context: Context, bikeAddress: String): ArrayList<Issue> {
        return FaqRepository(context).getIssues(bikeAddress)
    }

}