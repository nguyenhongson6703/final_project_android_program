package com.example.lastprojectandroidprogram.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.lastprojectandroidprogram.BottomBarScreen
import com.example.lastprojectandroidprogram.CourseScreeen
import com.example.lastprojectandroidprogram.HomeScreen
import com.example.lastprojectandroidprogram.MainHomeScreen
import com.example.lastprojectandroidprogram.detail_ui.LearnVocabulary
import com.example.lastprojectandroidprogram.test.Detail
import com.example.lastprojectandroidprogram.test.OverviewScreen


@Composable
fun HomeNavGraph(
    navController: NavHostController
){
    NavHost(navController = navController,
        route =  Graph.HOME,
        startDestination = BottomBarScreen.Home.route){

        composable(route = BottomBarScreen.Home.route) {
            MainHomeScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Course.route) {
           CourseScreeen()
        }
        composable(route = BottomBarScreen.Personal.route) {
           CourseScreeen()
        }
        detailsNavGraph(navController = navController)


    }
}
fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAIL,
        startDestination = DetailsScreen.Information.route
    ) {
        composable(route = DetailsScreen.Information.route
        ,  arguments = listOf(
                navArgument("id_course") {
                    type = NavType.IntType
                    defaultValue = 1
                },
                navArgument("current") {
                    type = NavType.IntType
                    defaultValue = 1
                },
                navArgument("des") {
                    type = NavType.IntType
                    defaultValue = 5
                },
                navArgument("point") {
                    type = NavType.IntType
                    defaultValue = 0
                }




            )) {
                backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt("id_course") ?: 1
            val current = backStackEntry.arguments?.getInt("current") ?: 1
            val des = backStackEntry.arguments?.getInt("des") ?: 5
            val point =  backStackEntry.arguments?.getInt("point") ?: 0

            
            Detail(idCourse =courseId , current = current , des =des , point = point, navController = navController)
        }
        composable(route = DetailsScreen.NewWord.route
            ,  arguments = listOf(
                navArgument("id_course") {
                    type = NavType.IntType
                    defaultValue = 1
                },
                navArgument("current") {
                    type = NavType.IntType
                    defaultValue = 1
                },
                navArgument("des") {
                    type = NavType.IntType
                    defaultValue = 5
                },
                navArgument("point") {
                    type = NavType.IntType
                    defaultValue = 0
                }




            )) {
                backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt("id_course") ?: 1
            val current = backStackEntry.arguments?.getInt("current") ?: 1
            val des = backStackEntry.arguments?.getInt("des") ?: 5
            val point =  backStackEntry.arguments?.getInt("point") ?: 0


            LearnVocabulary(idCourse =courseId , current = current , des =des , point = point, navController = navController)
        }
        composable(route = DetailsScreen.Overview.route,
            arguments = listOf(
                navArgument("point") {
                    type = NavType.IntType
                    defaultValue = 0
                }

            )
        ) {
                backStackEntry ->
            val point = backStackEntry.arguments?.getInt("point") ?: 0

            OverviewScreen(point = point , navComtroller = navController )
        }
    }
}


sealed class DetailsScreen(val route: String) {

    object Information : DetailsScreen(route = "information_screen?id_course={id_course}&current={current}&des={des}&point={point}") {
        fun passParams(
            id_course: Int,
            current: Int,
            des: Int,
            point: Int

        ): String {
            return "information_screen?id_course=$id_course&current=$current&des=$des&point=$point"
        }
    }
    object NewWord : DetailsScreen(route = "new_word_screen?id_course={id_course}&current={current}&des={des}&point={point}") {
        fun passParams(
            id_course: Int,
            current: Int,
            des: Int,
            point: Int

        ): String {
            return "new_word_screen?id_course=$id_course&current=$current&des=$des&point=$point"
        }
    }

    object Overview : DetailsScreen(route = "overview_screen?point={point}"){
        fun passParams(
            point: Int
        ): String {
            return "overview_screen?point=$point"
        }
    }
}




