package com.example.lastprojectandroidprogram.graphs


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.lastprojectandroidprogram.login.LoginScreen
import com.example.lastprojectandroidprogram.signup.SignUpScreen


fun NavGraphBuilder.authNavGraph(navController: NavHostController){
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ){
        composable(route = AuthScreen.Login.route){

            LoginScreen(onLoginClick = { navController.popBackStack()
                                       navController.navigate(Graph.HOME)},
                        onSignUpClick = {navController.navigate(AuthScreen.SignUp.route)})
        }
        composable(route = AuthScreen.SignUp.route){
           SignUpScreen(
               onSignUpClick = {  },
               onLoginClick = { navController.navigate(AuthScreen.Login.route) },
               onPolicyClick = {  },
               onPrivacyClick = {})
        }



    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")

}