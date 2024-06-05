package com.example.lastprojectandroidprogram.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lastprojectandroidprogram.R

@Composable
fun AppVocabularyBottomNavigation(modifier: Modifier = Modifier){
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier =  modifier
    ) {
        NavigationBarItem(
            selected = true ,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null)
            },
            label = {
                Text("Home")
            }

        )
        NavigationBarItem(
            selected = false ,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Category,
                    contentDescription = null)
            },
            label = {
                Text("Course")
            }

        )
        NavigationBarItem(
            selected = false ,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null)
            },
            label = {
                Text("Personal")
            }

        )
    }
}

@Composable
fun ProfileImage(
    drawableResource: Int,
    description: String,
    modifier: Modifier = Modifier,
    size: Int = 40
) {
    Image(
        modifier = modifier
            .size(size.dp)
            .clip(CircleShape),
        painter = painterResource(id = drawableResource),
        contentDescription = description,
    )
}
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                MaterialTheme.colorScheme.surface,
                CircleShape
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search course",
            modifier = Modifier.padding(start = 16.dp),
        )
        Text(
            text = "Search course",
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            style = MaterialTheme.typography.bodyMedium,
        )
        ProfileImage(
            drawableResource = R.drawable.avatar_6,
            description = "Sonny Nguyen",
            modifier = Modifier
                .padding(12.dp)
                .size(32.dp)
        )
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