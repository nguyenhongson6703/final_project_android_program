package com.example.lastprojectandroidprogram.detail_ui



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.lastprojectandroidprogram.components.ProgressBar
import com.example.lastprojectandroidprogram.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListenScreen() {
    val colorScheme = MaterialTheme.colorScheme.copy(
        background = Color(0xFF_FFFCF3),
        onBackground = Color.White
    )
    MaterialTheme(colorScheme = colorScheme) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = { Text(text = "TOEIC NÂNG CAO", style = TextStyle(fontWeight = FontWeight.Bold), fontSize = 25.sp) },
                navigationIcon = {
                    IconButton(onClick = { /*TODO: Handle back button click*/ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            ProgressBar(totalJobs = 20, completedJobs = 1)
            Spacer(modifier = Modifier.height(40.dp))
            VocabularyListen()
            Card(modifier = Modifier.padding(10.dp), // Thêm padding cho Card
                colors =  CardDefaults.cardColors(
                    containerColor = Color(0xFF_5DE7C0)
                ),
                shape = RoundedCornerShape(20.dp)) {
                Button(
                    onClick = { /*TODO: Handle button click*/ },
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
fun VocabularyListen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { /*TODO: Handle icon button click*/ }) {
            Icon(Icons.Filled.Campaign, contentDescription = null, modifier = Modifier.size(100.dp)) // Thêm Icon vào đây
        }
        Text(
            text = "(v)",
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
                ListButton(options = listOf("Shuttle", "Reschedule", "Requirement", "Rent"))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListenPreview() {
    AppTheme {
        ListenScreen()
    }
}
