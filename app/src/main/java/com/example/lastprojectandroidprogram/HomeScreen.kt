package com.example.lastprojectandroidprogram

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lastprojectandroidprogram.Response.CourseParticipateResponse
import com.example.lastprojectandroidprogram.ViewModel.CourseParticipateViewModel
import com.example.lastprojectandroidprogram.components.ProfileImage
import com.example.lastprojectandroidprogram.components.ProgressBar
import com.example.lastprojectandroidprogram.components.SearchBar
import com.example.lastprojectandroidprogram.graphs.Graph

@Composable
fun CourseListItem(

    isSelected: Boolean = false,
    modifier: Modifier = Modifier,
    courseParticipate: CourseParticipateResponse


    ) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .semantics { selected = isSelected }
            .clickable { //* navigation*//
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                ProfileImage(
                    drawableResource = R.drawable.toiec_image,
                    description = "Image course",

                    )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp, vertical = 4.dp)

                    ,
                    verticalArrangement = Arrangement.Center
                ){


                    Text(
                        text = "${courseParticipate.name}",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "${courseParticipate.startDate.substringBefore('T')} - 21-12-2025",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                }
                IconButton(
                    onClick = { /*Click Implementation*/ },
                    modifier = Modifier
                        .clip(CircleShape)

                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "more information",
                    )
                }

            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "${courseParticipate.percentCompleted}%",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "${courseParticipate.learnedWord}/${courseParticipate.quantityWords} mục đã học",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }

            }
            Spacer(modifier = Modifier.height(12.dp))
            ProgressBar(totalJobs = courseParticipate.quantityWords, completedJobs = courseParticipate.learnedWord)
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    ButtonRole(roleButton = "Ôn siêu tốc", imageVector = Icons.Default.WatchLater, courseParticipate )
                }
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    ButtonRole(roleButton = "Học từ mới", imageVector = Icons.Default.AutoStories, courseParticipate)
                }
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    ButtonRole(roleButton = "Ôn tập", imageVector = Icons.Default.MenuBook, courseParticipate )
                }



            }


        }
    }
}
@Composable
fun ButtonRole(roleButton : String, imageVector : ImageVector, courseParticipate: CourseParticipateResponse){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { /*TODO*/ },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.inversePrimary,
                contentColor = MaterialTheme.colorScheme.inverseOnSurface
            )

        ) {
            Icon(imageVector = imageVector,
                contentDescription = "Icon",
            )
        }
        Text(
            text = roleButton,
            style = MaterialTheme.typography.labelSmall
        )


    }
}
@Composable
fun MainHomeScreen(modifier: Modifier = Modifier,  courseParticipateViewModel: CourseParticipateViewModel = viewModel()){

    val courseParticipations by courseParticipateViewModel.courseParticipates.observeAsState(emptyList())
    val error by courseParticipateViewModel.error.observeAsState()

    // Fetch course participations
    courseParticipateViewModel.fetchCourseParticipates(Graph.TOKEN_ACCESS)
    Column(modifier) {
        SearchBar()
//        Column(
//            modifier = modifier
//                .verticalScroll(rememberScrollState())
//
//        ) {
//            CourseListItem()
//            CourseListItem()
//            CourseListItem()
//            CourseListItem()
//        }


        LazyColumn(modifier = Modifier.padding(bottom = 12.dp)) {
            items(courseParticipations) { participation ->
                CourseListItem(courseParticipate = participation)
            }
        }
        Spacer(modifier = Modifier.height(40.dp))


    }

}
