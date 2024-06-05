package com.example.lastprojectandroidprogram.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.lastprojectandroidprogram.BottomBarScreen
import com.example.lastprojectandroidprogram.CourseScreeen
import com.example.lastprojectandroidprogram.HomeScreen
import com.example.lastprojectandroidprogram.MainHomeScreen


@Composable
fun HomeNavGraph(
    navController: NavHostController
){
    NavHost(navController = navController,
        route =  Graph.HOME,
        startDestination = BottomBarScreen.Home.route){

        composable(route = BottomBarScreen.Home.route) {
            MainHomeScreen()
        }
        composable(route = BottomBarScreen.Course.route) {
           CourseScreeen()
        }
        composable(route = BottomBarScreen.Personal.route) {
           CourseScreeen()
        }

    }
}




