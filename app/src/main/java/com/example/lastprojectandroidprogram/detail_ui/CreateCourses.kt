package com.example.lastprojectandroidprogram.detail_ui
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import java.util.Date
import android.app.DatePickerDialog
import android.util.Log
import java.util.Calendar
import androidx.compose.ui.platform.LocalContext
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.TextField
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
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lastprojectandroidprogram.CallBackInterface.CourseCreateCallBack
import com.example.lastprojectandroidprogram.Response.CourseCreateResponse
import com.example.lastprojectandroidprogram.Service.createCourse
import com.example.lastprojectandroidprogram.graphs.DetailsScreen
import com.example.lastprojectandroidprogram.graphs.Graph
import com.example.lastprojectandroidprogram.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCoursesContent(navController: NavHostController) {
    val colorScheme = MaterialTheme.colorScheme.copy(
        background = Color(0xFFFFFCF3),
        onBackground = Color.White
    )

    MaterialTheme(colorScheme = colorScheme) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = "TẠO KHOÁ HỌC",
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

            CourseDetailsInput { idCourse, quantity ->
                run {
                    navController.navigate(
                        DetailsScreen.CreateVocabulary.passParams(
                            1,
                            idCourse,
                            quantity
                        )
                    )
                }
            }


        }
    }
}

@Composable
fun CourseDetailsInput(onClick: (Int, Int) -> Unit) {
    var courseName by remember { mutableStateOf("") }
    var courseDescription by remember { mutableStateOf("") }
    var quantityWords by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf(Calendar.getInstance()) }
    var endDate by remember { mutableStateOf(Calendar.getInstance()) }

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


            // Tên khoá học
            Text(
                text = "Tên Khoá Học:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TextField(
                value = courseName,
                onValueChange = { courseName = it },
                placeholder = {
                    Text(
                        text = "Nhập tên khoá học",
                        style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current.copy(alpha = 0.5f))
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Số lượng từ
            Text(
                text = "Số lượng từ:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TextField(
                value = quantityWords,
                onValueChange = { quantityWords = it },
                placeholder = {
                    Text(
                        text = "Nhập số từ",
                        style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current.copy(alpha = 0.5f))
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Ngày bắt đầu
            Text(
                text = "Ngày bắt đầu:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            DatePickerButton(
                selectedDate = startDate,
                onDateSelected = { startDate = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Ngày kết thúc
            Text(
                text = "Ngày kết thúc:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            DatePickerButton(
                selectedDate = endDate,
                onDateSelected = { endDate = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Mô tả khoá học
            Text(
                text = "Mô Tả Khoá Học:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TextField(
                value = courseDescription,
                onValueChange = { courseDescription = it },
                placeholder = {
                    Text(
                        text = "Nhập mô tả",
                        style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current.copy(alpha = 0.5f))
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )
        }
    }
    val context = LocalContext.current
    Button(
        onClick = {
                  createCourse(Graph.TOKEN_ACCESS, name = courseName, description = courseDescription,
                      quantiryWords = quantityWords.toInt(), object : CourseCreateCallBack{
                          override fun onResult(response: CourseCreateResponse?) {
                              if(response != null){
                                  Log.d("CreateCourse", "Create course successful: ${response.name}")
                                  Toast.makeText(context, "Create course successful with ${response.name}", Toast.LENGTH_SHORT).show()
                                  onClick(response.id, response.quantity_words)
                              }else{
                                  Log.d("CreateCourse", "Create course fail")
                                  Toast.makeText(context, "Create course fail", Toast.LENGTH_SHORT).show()

                              }

                          }

                      })
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Tạo Khoá Học",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold) // Adjust font size here
        )
    }
}

@Composable
fun DatePickerButton(
    selectedDate: Calendar,
    onDateSelected: (Calendar) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val datePickerDialog = remember { mutableStateOf(DatePickerDialog(context)) }

    Button(
        onClick = {
            datePickerDialog.value = DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, month, dayOfMonth)
                    onDateSelected(selectedDate)
                },
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.value.show()
        },
        modifier = modifier
    ) {
        Text("Chọn Ngày")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreateCoursesPreview() {
     AppTheme{
        //CreateCoursesContent()
    }
}