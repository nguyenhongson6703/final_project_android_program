package com.example.lastprojectandroidprogram.graphs


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lastprojectandroidprogram.HomeScreen
import com.example.lastprojectandroidprogram.Response.WordResponse

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController)
        composable(route = Graph.HOME) {
            HomeScreen()

        }
    }
}
object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val DETAIL = "detail_graph"
    var TOKEN_ACCESS = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxNCwiZXhwIjoxNzE3NzYyNzgxfQ.7LgC-N4IB1DEYPo6x0Qpi0pU8zUrTvlC40EKadZBjvA"
}
object  TotalAnswer{

    var listAnser = mutableListOf<WordResponse>()

}