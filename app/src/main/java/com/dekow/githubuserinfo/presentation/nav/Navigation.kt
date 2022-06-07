package com.dekow.githubuserinfo.presentation.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dekow.githubuserinfo.presentation.components.UserDetailScreen
import com.dekow.githubuserinfo.presentation.components.UsersListScreen
import com.dekow.githubuserinfo.presentation.screen_routes.Screens

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.UseListScreen.route){
        composable(route = Screens.UseListScreen.route){
           // UsersListScreen(navController = navController)
        }

        composable(
            route = Screens.UserDetailsScreen.route , //"?name = /{name}" for optional args

        ){
            UserDetailScreen(navController = navController)
        }
    }
}