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
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material.icons.filled.Campaign
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.lastprojectandroidprogram.CallBackInterface.ScoreCallBack
import com.example.lastprojectandroidprogram.R
import com.example.lastprojectandroidprogram.Response.WordResponse
import com.example.lastprojectandroidprogram.Service.ScoreVocabulary
import com.example.lastprojectandroidprogram.ViewModel.CourseViewModel
import com.example.lastprojectandroidprogram.ViewModel.WordViewModel
import com.example.lastprojectandroidprogram.components.ProgressBar
import com.example.lastprojectandroidprogram.graphs.DetailsScreen
import com.example.lastprojectandroidprogram.graphs.Graph
import com.example.lastprojectandroidprogram.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnVocabulary(idCourse: Int, current: Int, des: Int, point: Int, navController: NavHostController) {

    val wordModel: WordViewModel = viewModel()
    val word by wordModel.word.observeAsState()
    val error by wordModel.error.observeAsState()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        wordModel.fetchWord(idCourse, Graph.TOKEN_ACCESS)
    }


    val colorScheme = MaterialTheme.colorScheme.copy(
        background = Color(0xFF_FFFCF3),
        onBackground = Color.White
    )
    MaterialTheme(colorScheme = colorScheme) {
        Column(modifier = Modifier.fillMaxWidth().padding(bottom = 80.dp)) {
            TopAppBar(
                title = { Text(text = "TOEIC NÂNG CAO", style = TextStyle(fontWeight = FontWeight.Bold), fontSize = 25.sp) },
                navigationIcon = {
                    IconButton(onClick = { /*TODO: Handle back button click*/ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
                , colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.surfaceVariant)
            )
            Spacer(modifier = Modifier.height(20.dp))
            ProgressBar(totalJobs =des, completedJobs = current)
            Spacer(modifier = Modifier.height(30.dp))
            word?.let { Vocabulary(word = it, onNext = {
                if(current < des){
                    ScoreVocabulary(Graph.TOKEN_ACCESS,idCourse,it.id,object : ScoreCallBack{
                        override fun onResult(success: Boolean) {
                            if(success) {
                                Toast.makeText(context, "Tăng thêm 2 điểm", Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show()
                            }
                        }

                    })
                    navController.navigate(DetailsScreen.NewWord.passParams(idCourse, current + 1, des , point+1))
                }else{
                    navController.navigate(DetailsScreen.Overview.passParams(point))
                }

            }) }
        }
    }
}

@Composable
fun Vocabulary( word : WordResponse, onNext: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "ENGLISH",
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "${word.english}",
            style = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "NGHĨA",
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "${word.vietnamese}",
            style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Divider(modifier = Modifier
            .padding(vertical = 30.dp)
            .width(350.dp)
            .align(Alignment.CenterHorizontally), thickness = 3.dp)
        Text(
            text = "ẢNH MINH HOẠ",
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = rememberImagePainter(
                data = word.image_url,
                builder = {
                    transformations(RoundedCornersTransformation(16.dp.value))
                }
            ),
            contentDescription = "Illustrative image for 'Let up'",
            modifier = Modifier
                .padding(start = 30.dp)
                .size(280.dp)
                .clip(RoundedCornerShape(16.dp)), // Bo tròn 4 góc hình ảnh
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "TỪ LOẠI",
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.padding(start = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.padding(4.dp), // Thêm padding cho Card
                colors =  CardDefaults.cardColors(
                    containerColor = Color(0xFF_5DE7C0)
                ),
                shape = RoundedCornerShape(10.dp) // Bo tròn góc của Card
            ) {
                Text(
                    text = "${word.spell}",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(8.dp) // Thêm padding cho Text
                )
            }
            Spacer(modifier = Modifier.width(5.dp)) // Điều chỉnh khoảng cách giữa hai Text
            Card(
                modifier = Modifier.padding(4.dp),
                colors =  CardDefaults.cardColors(
                    containerColor = Color(0xFF_5DE7C0)
                ),
                shape = RoundedCornerShape(10.dp) // Bo tròn góc của Card
            ) {
                Text(
                    text = "${word.parts_of_speech}",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(8.dp) // Thêm padding cho Text
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "ÂM THANH",
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = { /*TODO: Handle button click*/ },
            modifier = Modifier
                .padding(start = 30.dp)
                .size(75.dp), // Đặt kích thước cho Button
            shape = RoundedCornerShape(16.dp), // Bo tròn góc của Button
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF_757575)
            )
        ) {
            Icon(Icons.Filled.Campaign, contentDescription = null, modifier = Modifier.size(100.dp))
        }
        Spacer(modifier = Modifier.height(20.dp))

        // New Button
        Button(
            onClick = onNext,
            modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp), // Round corners of new Button
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF_FFBB00) // Set color for new Button
            )
        ) {
            Text("> Tiếp tục", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LearnPreview() {
    AppTheme {
        //LearnVocabulary()
    }
}
