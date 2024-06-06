package com.example.lastprojectandroidprogram.detail_ui



import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lastprojectandroidprogram.CallBackInterface.ScoreCallBack
import com.example.lastprojectandroidprogram.Response.WordResponse
import com.example.lastprojectandroidprogram.Service.ScoreVocabulary
import com.example.lastprojectandroidprogram.ViewModel.WordReviewViewModel
import com.example.lastprojectandroidprogram.components.ProgressBar
import com.example.lastprojectandroidprogram.graphs.DetailsScreen
import com.example.lastprojectandroidprogram.graphs.Graph
import com.example.lastprojectandroidprogram.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListenScreen(idCourse: Int, current: Int, des: Int, point: Int, navController: NavHostController) {
    val colorScheme = MaterialTheme.colorScheme.copy(
        background = Color(0xFF_FFFCF3),
        onBackground = Color.White
    )
    val wordViewModel : WordReviewViewModel = viewModel()
    val words by wordViewModel.words.observeAsState()
    val error by wordViewModel.error.observeAsState()

    LaunchedEffect(Unit) {
        wordViewModel.fetchWords(idCourse, Graph.TOKEN_ACCESS)
    }




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
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            ProgressBar(totalJobs = des, completedJobs = current)
            Spacer(modifier = Modifier.height(40.dp))
            words?.let { VocabularyListen(it, idCourse, current, des,point, navController) }
            Card(modifier = Modifier.padding(10.dp), // Thêm padding cho Card
                colors =  CardDefaults.cardColors(
                    containerColor = Color(0xFF_5DE7C0)
                ),
                shape = RoundedCornerShape(20.dp)) {
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
                        .background(Color(0xFF_FFBB00)), // Đặt màu nền ở đây
                    colors = ButtonDefaults.textButtonColors( // Sử dụng textButtonColors
                        contentColor = Color.Black // Màu của nội dung trong nút
                    ),
                    shape = RoundedCornerShape(30.dp),
                ) {
                    Text(text = "> Tiếp tục", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
                }
            }

        }
    }
}

@Composable
fun VocabularyListen(words: List<WordResponse>,idCourse: Int, current: Int, des: Int, point: Int, navController: NavHostController) {
    val suffledWords = words.shuffled()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { /*TODO: Handle icon button click*/ }) {
            Icon(Icons.Filled.Campaign, contentDescription = null, modifier = Modifier.size(100.dp)) // Thêm Icon vào đây
        }
        Text(
            text = "${words[0].parts_of_speech}",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
            , colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                ButtonResult(word = suffledWords[0], idTrue = words[0].id, idCourse, current, des,point,  navController)
                ButtonResult(word = suffledWords[1], idTrue = words[0].id, idCourse, current, des,point, navController)
                ButtonResult(word = suffledWords[2], idTrue = words[0].id, idCourse, current, des,point, navController)
                ButtonResult(word = suffledWords[3], idTrue = words[0].id, idCourse, current, des,point, navController)



            }
        }
    }
}
@Composable
fun ButtonResult(word: WordResponse, idTrue : Int, idCourse: Int, current: Int, des: Int,point: Int,  navController: NavHostController){

    var buttonColor by remember { mutableStateOf(Color(0xFF_FFFCF3)) }
    val context = LocalContext.current
    Card(
        modifier = Modifier.padding(15.dp), // Thêm padding cho Card
        colors =  CardDefaults.cardColors(
            containerColor = Color(0xFF_5DE7C0)
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Button(
            onClick = {
                buttonColor = if (word.id == idTrue) Color.Green else Color.Red
                if(word.id == idTrue){


                            ScoreVocabulary(Graph.TOKEN_ACCESS,idCourse,word.id,object : ScoreCallBack {
                                override fun onResult(success: Boolean) {
                                    if(success) {
                                        Toast.makeText(context, "Tăng thêm 2 điểm", Toast.LENGTH_SHORT).show()
                                    }else{
                                        Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show()
                                    }
                                }

                            })




                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(buttonColor)
                .clip(RoundedCornerShape(12.dp)), // Đặt màu nền ở đây
            colors = ButtonDefaults.textButtonColors( // Sử dụng textButtonColors
                contentColor = Color.Black // Màu của nội dung trong nút
            ),

            // Bo góc của nút
        ) {
            Text(text = "${word.vietnamese}"  , style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)) // Kiểu chữ in đậm
        }
    }
}
@Composable
fun ButtonTranslateResult(word: WordResponse, idTrue : Int, idCourse: Int, current: Int, des: Int, point: Int, navController: NavHostController ){

    var buttonColor by remember { mutableStateOf(Color(0xFF_FFFCF3)) }
    val context = LocalContext.current
    Card(
        modifier = Modifier.padding(15.dp), // Thêm padding cho Card
        colors =  CardDefaults.cardColors(
            containerColor = Color(0xFF_5DE7C0)
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Button(
            onClick = {
                buttonColor = if (word.id == idTrue) Color.Green else Color.Red
                if(word.id == idTrue){

                    ScoreVocabulary(Graph.TOKEN_ACCESS,idCourse,word.id,object : ScoreCallBack {
                        override fun onResult(success: Boolean) {
                            if(success) {
                                Toast.makeText(context, "Tăng thêm 2 điểm", Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show()
                            }
                        }

                    })
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(buttonColor)
                .clip(RoundedCornerShape(12.dp)), // Đặt màu nền ở đây
            colors = ButtonDefaults.textButtonColors( // Sử dụng textButtonColors
                contentColor = Color.Black // Màu của nội dung trong nút
            ),

            // Bo góc của nút
        ) {
            Text(text = "${word.english}"  , style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)) // Kiểu chữ in đậm
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListenPreview() {
    AppTheme {
        //ListenScreen()
    }
}
