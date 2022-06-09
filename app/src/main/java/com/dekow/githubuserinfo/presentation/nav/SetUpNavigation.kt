package com.dekow.githubuserinfo.presentation.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dekow.githubuserinfo.presentation.components.SearchUserScreen
import com.dekow.githubuserinfo.presentation.components.UserDetailScreen
import com.dekow.githubuserinfo.presentation.components.UsersListScreen
import com.dekow.githubuserinfo.presentation.screen_routes.Screens

@Composable
fun SetUpNavigation(
    navController: NavHostController
) {
    //val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.UseListScreen.route
    ){
        composable(route = Screens.UseListScreen.route){
            UsersListScreen(navController = navController)
        }

        composable(route = Screens.UserSearchScreen.route){
            SearchUserScreen(navController = navController)
        }

        composable(
            route = Screens.UserDetailsScreen.route + "/{userName}" , //"?name = /{name}" for optional args
        ){
            UserDetailScreen(navController = navController)
        }
    }
}