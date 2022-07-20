package com.example.shercofaqapp.viewmodel.faq

import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.model.repositories.FaqRepository
import com.example.shercofaqapp.model.Issue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FaqViewModel
@Inject
constructor(
    private val faqRepository: FaqRepository
) : ViewModel() {

    fun getIssues(bikeAddress: String): ArrayList<Issue> {
        return faqRepository.getIssues(bikeAddress)
    }

}