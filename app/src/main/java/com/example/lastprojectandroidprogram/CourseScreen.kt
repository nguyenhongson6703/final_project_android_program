package com.example.lastprojectandroidprogram

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.lastprojectandroidprogram.components.SearchBar

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
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        CourseItem()
        CourseItem()
        CourseItem()
        CourseItem()
        CourseItem()

    }
}
@Composable
fun CourseItem(modifier: Modifier = Modifier, isSelected : Boolean = false){
    Surface(tonalElevation = 5.dp, modifier = modifier) {
        Card(
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .semantics { selected = isSelected }
                .clickable { //* navigation*//
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
                            text = "Sonny Nguyen",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color(android.graphics.Color.parseColor("#15a1ec"))
                        )
                    }


                }
                Text(text = "400 Words of TOEFL - Intermediate English", style = MaterialTheme.typography.headlineMedium
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
                    ) {
                        Icon(imageVector = Icons.Default.Groups, contentDescription = "num")
                        Text(text = "100.000", style = MaterialTheme.typography.titleSmall)
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .border(1.dp, Color.LightGray)
                            .padding(12.dp)
                    ) {
                        Icon(imageVector = Icons.Default.ListAlt, contentDescription = "list")
                        Text(text ="1200", style = MaterialTheme.typography.titleSmall)
                    }
                }
            }
        }


    }
}
