package com.example.shercofaqapp.model.repositories

import android.content.Context
import com.example.shercofaqapp.model.Issue
import com.example.shercofaqapp.model.Solution
import com.example.shercofaqapp.utils.JSONReader
import org.json.JSONException
import org.json.JSONObject

class FaqRepository(private val context: Context) {

    fun getIssues(bikeAddress: String): ArrayList<Issue> {
        // Instance of issues using the data model class
        val issuesArrayList: ArrayList<Issue> = ArrayList()

        try {
            val obj = JSONObject(JSONReader(context).getJSONFromAssets("faq-database.json")!!)
            val issuesArray = obj.getJSONArray(bikeAddress)

            //Get issues
            for (issueItem in 0 until issuesArray.length()) {
                val issueObject = issuesArray.getJSONObject(issueItem)
                val issueDescription = issueObject.getString("issueDescription")
                val issueSolutionsArray = issueObject.getJSONArray("issueSolutions")
                val solutionsArrayList: ArrayList<Solution> = ArrayList()

                //Get solutions from issues array
                for (solutionItem in 0 until issueSolutionsArray.length()) {
                    val issueSolutionObject = issueSolutionsArray.getJSONObject(solutionItem)
                    val solutionText = issueSolutionObject.getString("solutionText")
                    val solutionImage = issueSolutionObject.getInt("solutionImage")
                    val solution = Solution(
                        solutionText,
                        solutionImage
                    )
                    solutionsArrayList.add(solution)
                }

                val issue = Issue(issueDescription, solutionsArrayList)
                // add the issues in the list
                issuesArrayList.add(issue)
            }
        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }
        return issuesArrayList
    }
}