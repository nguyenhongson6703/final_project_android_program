package com.example.lastprojectandroidprogram.test

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun OverviewScreen(point: Int, navComtroller: NavHostController){
    Column {
        Text(text = "Point: $point")
        Button(onClick = {  }) {
            Text(text = "Return home")
        }
    }

}