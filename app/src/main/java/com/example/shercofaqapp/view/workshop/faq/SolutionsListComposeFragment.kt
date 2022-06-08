package com.example.shercofaqapp.view.workshop.faq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
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
                SetSolutionsList(solutionsArrayList, solutionTitle!!)
            }
        }

        return view
    }

}

@Composable
fun SolutionCard(solution: Solution) {

    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (isExpanded) 10.dp else 4.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Column(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp, bottom = 4.dp)
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            elevation = 4.dp,
            modifier = Modifier
                .animateContentSize()
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Column {
                    Text(
                        text = solution.solutionText,
                        color = Color(R.color.black),
                        modifier = Modifier
                            .padding(start = 4.dp, end = 4.dp, top = 4.dp)
                            .width(250.dp),
                        fontSize = 16.sp,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 2,
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(start = 4.dp, end = 4.dp, top = 4.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    OutlinedButton(
                        onClick = { isExpanded = !isExpanded },
                        modifier = Modifier
                            .width(48.dp)
                            .height(48.dp)
                    ) {
                        Image(painterResource(R.drawable.ic_baseline_arrow_drop_down_24), " ")
                    }
                }
            }
        }
    }
}

@Composable
fun SetSolutionsList(solutions: ArrayList<Solution>, solutionTitle: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = solutionTitle,
            color = Color.Gray,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }

    Box(
        modifier = Modifier
            .padding(top = 48.dp)
    ) {
        LazyColumn {
            items(solutions) { solution ->
                SolutionCard(solution)
            }
        }
    }
}