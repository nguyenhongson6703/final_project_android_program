package com.example.lastprojectandroidprogram.detail_ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.lastprojectandroidprogram.R
import com.example.lastprojectandroidprogram.graphs.AuthScreen
import com.example.lastprojectandroidprogram.graphs.Graph
import com.example.lastprojectandroidprogram.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalScreen(onLogOut: () -> Unit) {
    val colorScheme = MaterialTheme.colorScheme.copy(
        background = Color(0xFF_FFFCF3),
        onBackground = Color.White
    )
    MaterialTheme(colorScheme = colorScheme) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = { Text(text = "CÁ NHÂN", style = TextStyle(fontWeight = FontWeight.Bold), fontSize = 25.sp) },
                navigationIcon = {
                    IconButton(onClick = { /*TODO: Handle back button click*/ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.surfaceVariant)
            )
            Spacer(modifier = Modifier.height(30.dp))
            PersonalPage(){
                onLogOut()
            }
        }
    }
}

@Composable
fun PersonalPage(onLogOut : () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Avatar and Username
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(50))
                .size(100.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatar_6), // Replace with your avatar resource
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(50))
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "SonnyNguyen",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Divider(modifier = Modifier
            .padding(vertical = 30.dp)
            .width(350.dp)
            .align(Alignment.CenterHorizontally), thickness = 3.dp)
        // Information Section
        Section(title = "Information", icon = Icons.Filled.MoreVert)

        // Help & Feedback Section with Icon
        Section(title = "Help & Feedback", icon = Icons.Filled.QuestionMark)

        // Delete Data Section
        Section(title = "Delete Data", icon = Icons.Filled.Delete)

        // Version Section
        Section(title = "Version", icon = Icons.Filled.Info)

        Spacer(modifier = Modifier.height(100.dp))

        // Logout Button
        Button(
            onClick = onLogOut,
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(30.dp), // Set rounded corners here
            colors = ButtonDefaults.textButtonColors( // Use textButtonColors
                containerColor = Color(0xFF_FFBB00), // Button background color
                contentColor = Color.Black // Button content color
            ),
        ) {
            Icon(Icons.Filled.Logout, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp)) // Spacing between the icon and the text
            Text(text = "Logout", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
        }
    }
}

@Composable
fun Section(title: String, icon: ImageVector? = null) {
    Box(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (icon != null) {
                Icon(icon, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text
            }
            Text(text = title, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PersonalPreview() {
    AppTheme {
        //PersonalScreen()
    }
}