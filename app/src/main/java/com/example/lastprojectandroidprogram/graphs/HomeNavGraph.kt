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
import com.example.lastprojectandroidprogram.detail_ui.AnswerVocabulary
import com.example.lastprojectandroidprogram.detail_ui.DetailReviewScreen
import com.example.lastprojectandroidprogram.detail_ui.LearnVocabulary
import com.example.lastprojectandroidprogram.detail_ui.ResultScreen
import com.example.lastprojectandroidprogram.detail_ui.ReviewScreen
import com.example.lastprojectandroidprogram.test.Detail
import com.example.lastprojectandroidprogram.test.OverviewScreen
import kotlin.random.Random


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
        startDestination = DetailsScreen.NewWord.route
    ) {
//        composable(route = DetailsScreen.Information.route
//        ,  arguments = listOf(
//                navArgument("id_course") {
//                    type = NavType.IntType
//                    defaultValue = 1
//                },
//                navArgument("current") {
//                    type = NavType.IntType
//                    defaultValue = 1
//                },
//                navArgument("des") {
//                    type = NavType.IntType
//                    defaultValue = 5
//                },
//                navArgument("point") {
//                    type = NavType.IntType
//                    defaultValue = 0
//                }
//
//
//
//
//            )) {
//                backStackEntry ->
//            val courseId = backStackEntry.arguments?.getInt("id_course") ?: 1
//            val current = backStackEntry.arguments?.getInt("current") ?: 1
//            val des = backStackEntry.arguments?.getInt("des") ?: 5
//            val point =  backStackEntry.arguments?.getInt("point") ?: 0
//
//
//            Detail(idCourse =courseId , current = current , des =des , point = point, navController = navController)
//        }
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
        composable(route = DetailsScreen.ErrorWord.route
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
                },
                navArgument("id_vocabulary") {
                    type = androidx.navigation.NavType.IntType
                    defaultValue = 0
                }





            )) {
                backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt("id_course") ?: 1
            val current = backStackEntry.arguments?.getInt("current") ?: 1
            val des = backStackEntry.arguments?.getInt("des") ?: 5
            val point =  backStackEntry.arguments?.getInt("point") ?: 0
            val vocabularyId =  backStackEntry.arguments?.getInt("id_vocabulary") ?: 4


            AnswerVocabulary(idCourse =courseId , current = current , des =des , point = point, navController = navController, idVocabulary = vocabularyId)
        }
        composable(route = DetailsScreen.DetailReview.route
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


            DetailReviewScreen(idCourse =courseId , current = current , des =des , point = point, navController = navController)
        }
        composable(route = DetailsScreen.ReviewVocabulary.route
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
                navArgument("times") {
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
            val times = backStackEntry.arguments?.getInt("times") ?: 3
            val point =  backStackEntry.arguments?.getInt("point") ?: 0


           ReviewScreen(idCourse = courseId, current = current, des = des , point = point, times = times , navController = navController )
        }
        composable(route = DetailsScreen.Overview.route,
            arguments = listOf(
                navArgument("point") {
                    type = NavType.IntType
                    defaultValue = 0
                } ,
                navArgument("id_course") {
                    type = androidx.navigation.NavType.IntType
                    defaultValue = 0
                }

            )
        ) {
                backStackEntry ->
            val point = backStackEntry.arguments?.getInt("point") ?: 0
            val id_course = backStackEntry.arguments?.getInt("id_course") ?: 0

            //OverviewScreen(point = point , navComtroller = navController )
            ResultScreen(idCourse = id_course, point = point , navComtroller = navController)
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
    object DetailReview : DetailsScreen(route = "detail_review_screen?id_course={id_course}&current={current}&des={des}&point={point}") {
        fun passParams(
            id_course: Int,
            current: Int,
            des: Int,
            point: Int

        ): String {
            return "detail_review_screen?id_course=$id_course&current=$current&des=$des&point=$point"
        }
    }
    object ReviewVocabulary : DetailsScreen(route = "review_vocabulary_screen?id_course={id_course}&current={current}&des={des}&times={times}&point={point}") {
        fun passParams(
            id_course: Int,
            current: Int,
            des: Int,
            times: Int,
            point: Int

        ): String {
            return "review_vocabulary_screen?id_course=$id_course&current=$current&des=$des&times=$times&point=$point"
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
    object ErrorWord : DetailsScreen(route = "error_word_screen?id_course={id_course}&current={current}&des={des}&point={point}&id_vocabulary={id_vocabulary}") {
        fun passParams(
            id_course: Int,
            current: Int,
            des: Int,
            point: Int,
            id_vocabylary: Int

        ): String {
            return "error_word_screen?id_course=$id_course&current=$current&des=$des&point=$point&id_vocabulary=$id_vocabylary"
        }
    }

    object Overview : DetailsScreen(route = "overview_screen?point={point}&id_course={id_course}"){
        fun passParams(
            point: Int,
            id_course: Int
        ): String {
            return "overview_screen?point=$point&id_course=$id_course"
        }
    }
}




