package com.example.lastprojectandroidprogram

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.lastprojectandroidprogram.Response.CourseResponse
import com.example.lastprojectandroidprogram.ViewModel.CourseViewModel
import com.example.lastprojectandroidprogram.components.AppVocabularyBottomNavigation
import com.example.lastprojectandroidprogram.detail_ui.LearnVocabulary
import com.example.lastprojectandroidprogram.graphs.RootNavigationGraph
import com.example.lastprojectandroidprogram.login.LoginScreen
import com.example.lastprojectandroidprogram.signup.SignUpScreen
import com.example.lastprojectandroidprogram.ui.theme.AppTheme
import com.example.lastprojectandroidprogram.ui.theme.backgroundLight


class MainActivity : ComponentActivity() {
   // private val courseViewModel: CourseViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //courseViewModel.fetchCourses()
        setContent {
            AppTheme {


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                    tonalElevation = 5.dp
                ) {
                    RootNavigationGraph(navController = rememberNavController())
                }
            }
        }
    }
}

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CourseListScreen(courseViewModel: CourseViewModel) {
//    val courses by courseViewModel.courses.observeAsState(emptyList())
//    val error by courseViewModel.error.observeAsState()
//
//    Scaffold(
//        topBar = {
//            TopAppBar(title = { Text("Courses") })
//        }
//    ) {
//        if (error != null) {
//            Text(text = "Error: $error", color = MaterialTheme.colorScheme.error)
//        } else {
//            CourseList(courses = courses)
//        }
//    }
//}
//
//@Composable
//fun CourseList(courses: List<CourseResponse>) {
//    LazyColumn {
//        items(courses) { course ->
//            CourseItem(course)
//        }
//    }
//}
//
//@Composable
//fun CourseItem(course: CourseResponse) {
//    Card(
//        modifier = Modifier.fillMaxSize().padding(8.dp)
//
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = "Name: ${course.name}", style = MaterialTheme.typography.bodyMedium)
//            Text(text = "Description: ${course.description}", style = MaterialTheme.typography.bodyMedium)
//            Text(text = "Start Date: ${course.startDate}", style = MaterialTheme.typography.bodyMedium)
//            Text(text = "Quantity Words: ${course.quantityWords}", style = MaterialTheme.typography.bodyMedium)
//            Text(text = "Username: ${course.username}", style = MaterialTheme.typography.bodyMedium)
//        }
//    }
//}






















@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun GreetingPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            tonalElevation = 5.dp
        ) {
            Scaffold(
                bottomBar = {
                    AppVocabularyBottomNavigation()
                }
            ) {
                    paddingValues ->  CourseScreeen(Modifier.padding(paddingValues))
            }
        }
    }
}