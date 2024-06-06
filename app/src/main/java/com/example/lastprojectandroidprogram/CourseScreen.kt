package com.example.lastprojectandroidprogram


import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lastprojectandroidprogram.CallBackInterface.ParticipateCallBack
import com.example.lastprojectandroidprogram.Response.CourseResponse
import com.example.lastprojectandroidprogram.Service.participateCourse
import com.example.lastprojectandroidprogram.ViewModel.CourseViewModel
import com.example.lastprojectandroidprogram.components.SearchBar
import com.example.lastprojectandroidprogram.graphs.Graph
import androidx.compose.ui.platform.LocalContext
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun CourseScreeen(modifier: Modifier = Modifier){
    Column(modifier) {
        HeadLineCoure()
        SearchBar()
        CoureContent()

    }
}
@Composable
fun HeadLineCoure(modifier: Modifier = Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
        , horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = "Khóa học"
        , style = MaterialTheme.typography.headlineLarge)
        Button(onClick = { /*TODO*/ },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            )
            ) {
            Text(text = "Thêm khóa học ", style = MaterialTheme.typography.labelMedium)
        }
    }
}
@Composable
fun CoureContent(modifier : Modifier = Modifier){
    val courseViewModel: CourseViewModel = viewModel()
    val courses by courseViewModel.courses.observeAsState(emptyList())
    val error by courseViewModel.error.observeAsState()

    // Gọi fetchCourses ngay khi ViewModel được khởi tạo
    LaunchedEffect(Unit) {
        courseViewModel.fetchCourses()
    }
    LazyColumn(modifier = Modifier.padding(bottom = 80.dp)) {
        items(courses) { course ->
            CourseItem(course = course)
        }
    }


}
@Composable
fun CourseItem(modifier: Modifier = Modifier, isSelected : Boolean = false, course: CourseResponse){
    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    Surface(tonalElevation = 5.dp, modifier = modifier) {
        Card(
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .semantics { selected = isSelected }
                .clickable {
                    showDialog.value = true
                },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
        ) {
            Column {
                Image(painter = painterResource(id = R.drawable.cour_example),
                    contentDescription = null ,
                    contentScale = ContentScale.Crop
                    ,   modifier = Modifier.fillMaxWidth())
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                    , horizontalArrangement =  Arrangement.SpaceBetween
                ){
                    Text(
                        text = "Tiếng anh",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color(android.graphics.Color.parseColor("#15a1ec"))
                    )
                    Row(

                    ) {
                        Text(text = "tạo bởi ", style = MaterialTheme.typography.labelMedium)
                        Text(
                            text = "${course.username}",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color(android.graphics.Color.parseColor("#15a1ec"))
                        )
                    }


                }
                Text(text = "${course.name}", style = MaterialTheme.typography.headlineMedium
                    , modifier = Modifier.padding(12.dp)
                )
                Text(text = "${course.description}", style = MaterialTheme.typography.bodyMedium
                    , modifier = Modifier.padding(12.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .clip(RoundedCornerShape(4.dp))


                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .border(1.dp, Color.LightGray)
                            .padding(12.dp)
                            , horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Groups, contentDescription = "num")
                        Text( modifier = Modifier.padding(4.dp),text = "${course.id}", style = MaterialTheme.typography.titleSmall)
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .border(1.dp, Color.LightGray)
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(imageVector = Icons.Default.ListAlt, contentDescription = "list")
                        Text( modifier = Modifier.padding(4.dp), text ="${course.quantityWords}", style = MaterialTheme.typography.titleSmall)
                    }
                }
            }
        }


    }
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showDialog.value = false
            },
            title = {
                Text(text = "Tham gia khóa học")
            },
            text = {
                Text("Bạn có muốn tham gia khóa học này không?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        participateCourse(Graph.TOKEN_ACCESS, course.id, object : ParticipateCallBack {
                            override fun onResult(success: Boolean) {
                                if (success) {
                                    Toast.makeText(context, "Tham gia khóa học thành công", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(context, "Không thể tham gia khóa học", Toast.LENGTH_SHORT).show()
                                }
                            }
                        })
                        showDialog.value = false

                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog.value = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}
