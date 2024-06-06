package com.example.lastprojectandroidprogram.test

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.lastprojectandroidprogram.graphs.DetailsScreen

@Composable
fun Detail( idCourse: Int, current: Int, des: Int, point: Int, navController: NavHostController){
    Column {
        Text(text = "Course: $idCourse")
        Text(text = "Current: $current")
        Text(text = "Destination: $des")
        Text(text = "Point: $point")
        Button(onClick = {
            if(current < des){
              navController.navigate(DetailsScreen.Information.passParams(idCourse, current + 1, des , point+2))
            }else{
                navController.navigate(DetailsScreen.Overview.passParams(point))
            }


        }) {
            Text(text = "Next")
        }
    }
}