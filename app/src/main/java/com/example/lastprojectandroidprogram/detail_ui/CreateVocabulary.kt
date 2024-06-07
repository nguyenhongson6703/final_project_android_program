package com.example.lastprojectandroidprogram.detail_ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import android.net.Uri
import androidx.compose.material3.LocalContentColor
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.TextField
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
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
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.lastprojectandroidprogram.CallBackInterface.VocabularyCreateCallBack
import com.example.lastprojectandroidprogram.Response.WordResponse
import com.example.lastprojectandroidprogram.Service.createVocabulary
import com.example.lastprojectandroidprogram.graphs.DetailsScreen
import com.example.lastprojectandroidprogram.graphs.Graph
import com.example.lastprojectandroidprogram.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateVocabularyContent(current: Int , destination: Int , idCourse: Int,navController: NavHostController ) {
    val colorScheme = MaterialTheme.colorScheme.copy(
        background = Color(0xFFFFFCF3),
        onBackground = Color.White
    )

    MaterialTheme(colorScheme = colorScheme) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp, bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = "QUAY LẠI KHOÁ HỌC",
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        fontSize = 25.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO: Handle back button click*/ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.surfaceVariant)
            )

            Spacer(modifier = Modifier.height(3.dp))

            VocabularyCreate(idCourse) {
                if (current < destination) {
                    navController.navigate(
                        DetailsScreen.CreateVocabulary.passParams(
                            current + 1,
                            idCourse,
                            destination
                        )
                    )
                } else {
                    navController.navigate(Graph.HOME)
                }
            }
        }
    }
}

@Composable
fun VocabularyCreate( idCourse: Int,onClick: () -> Unit ) {
    var english by remember { mutableStateOf("") }
    var vietnamese by remember { mutableStateOf("") }
    var spell by remember { mutableStateOf("") }
    var part_of_speech by remember {
        mutableStateOf("")
    }
    var audioUri by remember { mutableStateOf<Uri?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var example by remember { mutableStateOf("") }
    var exampleTranslate by remember {
        mutableStateOf("")
    }
    val audioLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        audioUri = uri
    }
    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Word:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TextField(
                value =english,
                onValueChange = { english = it },
                placeholder = {
                    Text(
                        text = "Nhập từ vựng",
                        style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current.copy(alpha = 0.5f))
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Definition:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TextField(
                value = vietnamese,
                onValueChange = { vietnamese = it },
                placeholder = {
                    Text(
                        text = "Nhập nghĩa của từ",
                        style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current.copy(alpha = 0.5f))
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Audio Section
            Text(
                text = "Audio:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Button(
                onClick = { audioLauncher.launch("audio/*") },
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Text("Tải lên")
            }

            Spacer(modifier = Modifier.height(16.dp))

            audioUri?.let {
                Text(text = "File đã chọn: ${it.lastPathSegment}", maxLines = 1, overflow = TextOverflow.Ellipsis)

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        mediaPlayer?.release()
                        mediaPlayer = MediaPlayer().apply {
                            setDataSource(it.toString())
                            prepare()
                            start()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Phát Audio")
                }
            }

            // Image Section
            Text(
                text = "Image:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Button(
                onClick = { imageLauncher.launch("image/*") },
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Text("Tải lên")
            }

            Spacer(modifier = Modifier.height(16.dp))

            imageUri?.let {
                Text(text = "File đã chọn: ${it.lastPathSegment}", maxLines = 1, overflow = TextOverflow.Ellipsis)

                Spacer(modifier = Modifier.height(8.dp))

                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = "Pronunciation:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TextField(
                value = spell,
                onValueChange = { spell = it },
                placeholder = {
                    Text(
                        text = "Phát âm",
                        style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current.copy(alpha = 0.5f))
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Part of Speech:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TextField(
                value = part_of_speech,
                onValueChange = { part_of_speech = it },
                placeholder = {
                    Text(
                        text = "Từ loại",
                        style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current.copy(alpha = 0.5f))
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Example:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TextField(
                value = example,
                onValueChange = { example = it },
                placeholder = {
                    Text(
                        text = "Ví dụ",
                        style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current.copy(alpha = 0.5f))
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )
            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "Example translate:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TextField(
                value = exampleTranslate,
                onValueChange = { exampleTranslate = it },
                placeholder = {
                    Text(
                        text = "Nghĩa ví dụ",
                        style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current.copy(alpha = 0.5f))
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )
            Spacer(modifier = Modifier.height(16.dp))
            val context = LocalContext.current
            Button(
                onClick = {
                   createVocabulary(Graph.TOKEN_ACCESS, english, vietnamese, spell,
                       part_of_speech, idCourse,example,exampleTranslate,object: VocabularyCreateCallBack{
                       override fun onResult(response: WordResponse?) {
                           if(response != null){
                               Log.d("CreateVocabulary", "Create vocabulary successful: ${response.english}")
                               Toast.makeText(context, "Create vocabulary successful with ${response.english}", Toast.LENGTH_SHORT).show()
                                onClick()

                           }else{
                               Log.d("CreateVocabulary", "Create vocabulary fail")
                               Toast.makeText(context, "Create vocabulary fail", Toast.LENGTH_SHORT).show()
                                onClick()
                           }

                       }

                   })
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("Lưu và tiếp tục")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreateVocabularyPreview() {
    AppTheme {
        //CreateVocabularyContent()
    }
}