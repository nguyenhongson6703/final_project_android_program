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
import androidx.compose.runtime.saveable.rememberSaveable
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
fun VocabularyTranslateScreen(idCourse: Int, current: Int, des: Int, point: Int, navController: NavHostController) {
    val wordViewModel : WordReviewViewModel = viewModel()
    val words by wordViewModel.words.observeAsState()
    val error by wordViewModel.error.observeAsState()

    LaunchedEffect(Unit) {
        wordViewModel.fetchWords(1, Graph.TOKEN_ACCESS)
    }

    val (trueAnswer, setTrueAnswer) = rememberSaveable {
        mutableStateOf(true)
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
            words?.let { VocabularyTranslateCard(it, idCourse, current, des,point, navController, {setTrueAnswer(false)}) }
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
                            if(trueAnswer == true){
                                navController.navigate(DetailsScreen.DetailReview.passParams(idCourse, current + 1, des , point+2))

                            }else{
                                val route : String? = words?.get(0)?.let {
                                    DetailsScreen.ErrorWord.passParams(idCourse,current,des,point,
                                        it.id)
                                }
                                if(route != null){
                                    navController.navigate(route)
                                }

                            }

                        }else{
                            navController.navigate(DetailsScreen.Overview.passParams(point, idCourse))
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
fun VocabularyTranslateCard(words: List<WordResponse>,idCourse: Int, current: Int, des: Int, point: Int, navController: NavHostController, setAnswer: () -> Unit) {
    val shuffledWords = remember { words.shuffled() }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${words[0].english}",
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
                shuffledWords.forEachIndexed { index, word ->
                    ButtonResult(
                        word = word,
                        idTrue = words[0].id,
                        idCourse,
                        current,
                        des,
                        point,
                        navController,
                        setAnswer

                    )
                }
            }
        }
    }
}





@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CheckTranslatePreview() {
    AppTheme {
        //VocabularyTranslateScreen()
    }
}
