package com.example.lastprojectandroidprogram.detail_ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import kotlin.random.Random

@Composable
fun DetailReviewScreen(idCourse: Int, current: Int, des: Int, point: Int, navController: NavHostController){

    if (current == 1){
        ListenScreen(idCourse, current, des, point, navController)

    }else if(current == 2){
        VocabularyScreen(idCourse, current, des, point, navController)

    }else if (current == 3){
        VocabularyTranslateScreen(idCourse, current, des, point, navController)

    }else if (current == 4){
        VocabularyScreen(idCourse, current, des, point, navController)

    }else if(current == 5){
        VocabularyTranslateScreen(idCourse, current, des, point, navController)
    }else if(current == 6){
        VocabularyScreen(idCourse, current, des, point, navController)

    }else if (current == 7){
        VocabularyTranslateScreen(idCourse, current, des, point, navController)

    }else if (current == 8){
        VocabularyScreen(idCourse, current, des, point, navController)

    }else{
        VocabularyTranslateScreen(idCourse, current, des, point, navController)
    }

}