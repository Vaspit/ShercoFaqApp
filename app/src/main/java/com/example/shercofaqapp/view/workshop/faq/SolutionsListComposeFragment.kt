package com.example.shercofaqapp.view.workshop.faq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.example.shercofaqapp.R
import com.example.shercofaqapp.model.Solution
import kotlinx.coroutines.NonDisposableHandle.parent

class SolutionsListComposeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val solutionTitle = arguments?.getString("currentIssueDescription")
        val solutionsArrayList = arguments?.get("currentIssueSolutions") as ArrayList<Solution>
        val view = ComposeView(requireContext())

        view.apply {
            setContent {
                SetSolutionsList(solutionsArrayList)
            }
        }

        return view
    }

}

@Composable
fun SolutionCard(solution: Solution) {

    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.clickable{ isExpanded = !isExpanded }.padding(4.dp) ,
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            elevation = 4.dp,
            modifier = Modifier.animateContentSize().fillMaxWidth()
        ) {
            Text(
                text = solution.solutionText,
                color = Color(R.color.black),
                modifier = Modifier.padding(all = 4.dp),
                fontSize = 20.sp,
                maxLines = if (isExpanded) Int.MAX_VALUE else 1,
            )
        }
    }
}

@Composable
fun SetSolutionsList(solutions: ArrayList<Solution>) {
    LazyColumn {
        items(solutions) { solution ->
            SolutionCard(solution)
        }
    }
}