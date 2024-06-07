package com.example.lastprojectandroidprogram.detail_ui



import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.lastprojectandroidprogram.graphs.DetailsScreen
import com.example.lastprojectandroidprogram.graphs.Graph
import com.example.lastprojectandroidprogram.graphs.TotalAnswer
import com.example.lastprojectandroidprogram.ui.theme.AppTheme

import kotlinx.coroutines.delay




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen(idCourse: Int, current: Int, des: Int, point: Int,times: Int, navController: NavHostController) {
    val colorScheme = MaterialTheme.colorScheme.copy(
        background = Color(0xFF_FFFCF3),
        onBackground = Color.White
    )
    val (timesRest, setTimesRest) = rememberSaveable {
        mutableStateOf(times)
    }

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
                },
                colors = TopAppBarDefaults.topAppBarColors( MaterialTheme.colorScheme.surfaceVariant)
            )
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (time in 1..timesRest){
                    IconButton(onClick = { /*TODO: Handle first icon button click*/ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null, modifier = Modifier.size(48.dp))
                    }
                }

//                IconButton(onClick = { /*TODO: Handle second icon button click*/ }) {
//                    Icon(Icons.Filled.Favorite, contentDescription = null, modifier = Modifier.size(48.dp))
//                }
//                IconButton(onClick = { /*TODO: Handle third icon button click*/ }) {
//                    Icon(Icons.Filled.Favorite, contentDescription = null, modifier = Modifier.size(48.dp))
//                }
            }
            ProgressBar1(totalJobs = 32)
            Spacer(modifier = Modifier.height(30.dp))
            words?.let {
                ReviewVocabulary(words = it, idCourse = idCourse ) {
                    setTimesRest(times-1)
                }
            }
            Card(modifier = Modifier.padding(3.dp), // Thêm padding cho Card
                colors =  CardDefaults.cardColors(
                    containerColor = Color(0xFF_5DE7C0)
                ),
                shape = RoundedCornerShape(10.dp)) {
                Button(
                    onClick = {
                        if(current < des&& timesRest >0){
                            navController.navigate(DetailsScreen.ReviewVocabulary.passParams(idCourse, current + 1, des, timesRest , point+2))
                        }else{
                            navController.navigate(DetailsScreen.Overview.passParams(point, idCourse))
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
fun ProgressBar1(totalJobs: Int) {
    var currentJob by remember { mutableStateOf(totalJobs) } // Khởi tạo currentJob với totalJobs

    LaunchedEffect(key1 = currentJob) {
        while (currentJob > 0) {
            delay(1000L) // Delay 1 second
            currentJob--
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .clip(RoundedCornerShape(10.dp)) // Bo tròn các góc với bán kính 10dp
            .background(Color(0xFFE7EAF0))
    ) {
        val progress = currentJob / totalJobs.toFloat() // Di chuyển dòng này vào đây

        Box(
            modifier = Modifier
                .fillMaxWidth(fraction = progress)
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp)) // Áp dụng bo tròn cho phần đã hoàn thành
                .background(Color(0xFFFFBB00))
        )
    }
}

@Composable
fun ReviewVocabulary(words: List<WordResponse>, idCourse: Int, onSetTime: () -> Unit) {

    val shuffledWords = remember { words.shuffled() }
    var showCorrect by remember { mutableStateOf(false) }
    var clickedIndex by remember { mutableStateOf(-1) }

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
        Spacer(modifier = Modifier.height(20.dp))
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
                    ButtonResultReview(
                        word = word,
                        isCorrect = word.id == words[0].id,
                        isClicked = clickedIndex == index,
                        showCorrect = showCorrect,
                        onClick = {
                            clickedIndex = index
                            if (word.id == words[0].id) {
                                showCorrect = true
                            } else {
                                onSetTime()
                                showCorrect = true
                            }
                        }, idCourse
                    )
                }
            }
        }
    }
}
@Composable
fun ButtonResultReview(
    word: WordResponse,
    isCorrect: Boolean,
    isClicked: Boolean,
    showCorrect: Boolean,
    onClick: () -> Unit,
    idCourse: Int,

) {
    val buttonColor = when {
        showCorrect && isCorrect -> Color.Green
        isClicked -> Color.Red
        else -> Color(0xFF_FFFCF3)
    }




    val context = LocalContext.current

    Card(
        modifier = Modifier.padding(15.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF_5DE7C0)),
        shape = RoundedCornerShape(10.dp)
    ) {
        Button(
            onClick = {
                onClick()
                if (isCorrect) {
                    ScoreVocabulary(Graph.TOKEN_ACCESS, idCourse, word.id, object : ScoreCallBack {
                        override fun onResult(success: Boolean) {
                            if (success) {
                                Toast.makeText(context, "Tăng thêm 2 điểm", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                    TotalAnswer.listAnser.add(word)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(buttonColor)
                .clip(RoundedCornerShape(12.dp)),
            colors = ButtonDefaults.textButtonColors(contentColor = Color.Black)
        ) {
            Text(
                text = word.english,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ReviewPreview() {
    AppTheme {
        //ReviewScreen()
    }
}
