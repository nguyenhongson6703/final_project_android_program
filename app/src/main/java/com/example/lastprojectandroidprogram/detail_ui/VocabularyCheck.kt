package com.example.lastprojectandroidprogram.detail_ui

import com.example.lastprojectandroidprogram.ui.theme.AppTheme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lastprojectandroidprogram.Response.WordResponse
import com.example.lastprojectandroidprogram.ViewModel.WordReviewViewModel
import com.example.lastprojectandroidprogram.graphs.DetailsScreen
import com.example.lastprojectandroidprogram.graphs.Graph


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VocabularyScreen(idCourse: Int, current: Int, des: Int, point: Int, navController: NavHostController) {

    val wordViewModel : WordReviewViewModel = viewModel()
    val words by wordViewModel.words.observeAsState()
    val error by wordViewModel.error.observeAsState()

    LaunchedEffect(Unit) {
        wordViewModel.fetchWords(idCourse, Graph.TOKEN_ACCESS)
    }

    val colorScheme = MaterialTheme.colorScheme.copy(
        background = Color(0xFF_FFFCF3),
        onBackground = Color.White
    )

    MaterialTheme(colorScheme = colorScheme) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 80.dp)
            .verticalScroll(
                rememberScrollState()
            )) {
            TopAppBar(
                title = { Text(text = "TOEIC NÂNG CAO", style = TextStyle(fontWeight = FontWeight.Bold), fontSize = 25.sp) },
                navigationIcon = {
                    IconButton(onClick = { /*TODO: Handle back button click*/ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.surfaceVariant)
            )
            Spacer(modifier = Modifier.height(20.dp))
            ProgressBar(totalJobs = des, completedJobs = current)
            Spacer(modifier = Modifier.height(20.dp))
            words?.let { VocabularyCard(it, idCourse, current, des,point, navController) }
            Card(
                modifier = Modifier.padding(4.dp), // Thêm padding cho Card
                colors =  CardDefaults.cardColors(
                    containerColor = Color(0xFF_5DE7C0)
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Button(
                    onClick = {
                        if(current < des){
                            navController.navigate(DetailsScreen.DetailReview.passParams(idCourse, current + 1, des , point+2))
                        }else{
                            navController.navigate(DetailsScreen.Overview.passParams(point))
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF_FFBB00)),
                    shape = CircleShape,
                    colors = ButtonDefaults.textButtonColors( // Sử dụng textButtonColors
                        contentColor = Color.Black // Màu của nội dung trong nút
                    ),
                ) {
                    Text(text = "> Tiếp tục", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
                }
            }

        }
    }
}

@Composable
fun ProgressBar(totalJobs: Int, completedJobs: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .clip(RoundedCornerShape(10.dp)) // Bo tròn các góc với bán kính 10dp
            .background(Color(0xFFE7EAF0))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(fraction = completedJobs / totalJobs.toFloat())
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp)) // Áp dụng bo tròn cho phần đã hoàn thành
                .background(Color(0xFFFFBB00))
        )
    }
}

@Composable
fun VocabularyCard(words : List<WordResponse>  ,idCourse: Int, current: Int, des: Int, point: Int, navController: NavHostController) {
    val wordsShuffle = words.shuffled()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${words[0].vietnamese}",
            style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.Bold)
        )
        Text(
            text = "${words[0].parts_of_speech}",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                ButtonTranslateResult(word = wordsShuffle[0], idTrue = words[0].id, idCourse, current, des,point,  navController )
                ButtonTranslateResult(word = wordsShuffle[1], idTrue = words[0].id, idCourse, current, des,point,  navController )
                ButtonTranslateResult(word = wordsShuffle[2], idTrue = words[0].id, idCourse, current, des,point,  navController )
                ButtonTranslateResult(word = wordsShuffle[3], idTrue = words[0].id , idCourse, current, des,point,  navController)
            }
        }
    }
}


//@Composable
//fun ButtonResult(text : String){
//    Card(
//        modifier = Modifier.padding(15.dp), // Thêm padding cho Card
//        colors =  CardDefaults.cardColors(
//            containerColor = Color(0xFF_5DE7C0)
//        ),
//        shape = RoundedCornerShape(10.dp)
//    ) {
//        Button(
//            onClick = {},
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color(0xFF_FFFCF3))
//                .clip(RoundedCornerShape(12.dp)), // Đặt màu nền ở đây
//            colors = ButtonDefaults.textButtonColors( // Sử dụng textButtonColors
//                contentColor = Color.Black // Màu của nội dung trong nút
//            ),
//
//            // Bo góc của nút
//        ) {
//            Text(text = text , style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)) // Kiểu chữ in đậm
//        }
//    }
//}
@Composable
fun ListButton(options: List<String>){

    LazyColumn {
        items(options){
                //item -> ButtonResult(text = item)
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CheckPreview() {
    AppTheme {
        //VocabularyScreen()
    }
}
