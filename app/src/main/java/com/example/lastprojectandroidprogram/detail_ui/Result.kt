package com.example.lastprojectandroidprogram.detail_ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.draw.rotate
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.navigation.NavHostController
import com.example.lastprojectandroidprogram.Response.WordResponse
import com.example.lastprojectandroidprogram.components.ProgressBar
import com.example.lastprojectandroidprogram.graphs.DetailsScreen
import com.example.lastprojectandroidprogram.graphs.TotalAnswer
import com.example.lastprojectandroidprogram.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(modifier: Modifier = Modifier, idCourse : Int, point: Int, navComtroller: NavHostController) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(bottom = 100.dp)
        .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            title = { Text(text = "TOEIC NÂNG CAO", style = TextStyle(fontWeight = FontWeight.Bold), fontSize = 25.sp) },
            navigationIcon = {
                IconButton(onClick = { /*TODO: Handle back button click*/ }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
                }
            }
            , colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.surfaceVariant)
        )
        Text(
            text = "Bạn đã hoàn thành bài học!",
            style = TextStyle(fontWeight = FontWeight.Bold),
            fontSize = 25.sp,
            modifier = Modifier
                .padding(16.dp)
                .rotate(-2f)  // Nghiêng 15 độ
                .align(Alignment.CenterHorizontally)
        )
        ProgressCard(point)
        Column(modifier = Modifier
            .fillMaxWidth()
        ) {
            AchievementCard(TotalAnswer.listAnser)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly  // Căn chỉnh các nút cho đều
        ){
            ButtonRole(role = 1, imageVector = Icons.Default.MenuBook, idCourse, navComtroller )
            ButtonRole(role = 2, imageVector = Icons.Default.AutoStories, idCourse, navComtroller )
            ButtonRole(role = 3, imageVector = Icons.Default.WatchLater, idCourse, navComtroller)
        }
    }
}


@Composable
fun ProgressCard(point: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Text(
            text = "Tiến độ của bạn",
            style = TextStyle(fontWeight = FontWeight.Bold),
            fontSize = 25.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Điểm đã đạt được",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "$point",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    fontSize = 25.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Mục tiêu hàng ngày của bạn",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(30.dp))
                ProgressBar(totalJobs = 20, completedJobs = 15)
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Điểm còn cần đạt: 3384",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun AchievementCard(achievements: List<WordResponse>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Text(
            text = "Bạn vừa đạt được",
            style = TextStyle(fontWeight = FontWeight.Bold),
            fontSize = 25.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(16.dp)

        ) {
            items(achievements){
                    item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = item.english,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        fontSize = 18.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = item.vietnamese,
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun ButtonRole(role : Int, imageVector : ImageVector, idCourse: Int, navComtroller: NavHostController){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            TotalAnswer.listAnser.clear()

            if(role == 1){

                navComtroller.navigate(DetailsScreen.ReviewVocabulary.passParams(id_course = idCourse, current = 1, des = 20, times = 3, point = 0))
            }else if(role == 2){
                navComtroller.navigate(DetailsScreen.NewWord.passParams(id_course = idCourse, current = 1, des = 9, point = 0))

            }else{
                navComtroller.navigate(DetailsScreen.DetailReview.passParams(id_course = idCourse, current = 1, des = 9, point = 0))

        }
        },
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
            text = if (role == 1) "Ôn siêu tốc"
            else if(role == 2) "Học từ mới"
            else "Ôn tập",
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ResultPreview() {
    AppTheme {
       // ResultScreen()
    }
}
