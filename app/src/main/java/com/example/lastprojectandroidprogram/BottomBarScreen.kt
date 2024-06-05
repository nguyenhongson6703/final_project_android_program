package com.example.lastprojectandroidprogram

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "HOME",
        title = "HOME",
        icon = Icons.Default.Home
    )

    object Course : BottomBarScreen(
        route = "COURSE",
        title = "COURSE",
        icon = Icons.Default.Category
    )

    object Personal : BottomBarScreen(
        route = "PERSONAL",
        title = "PERSONAL",
        icon = Icons.Default.AccountCircle
    )
}