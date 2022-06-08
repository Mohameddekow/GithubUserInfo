package com.dekow.githubuserinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dekow.githubuserinfo.presentation.components.SearchUserScreen
import com.dekow.githubuserinfo.presentation.components.UserDetailScreen
import com.dekow.githubuserinfo.presentation.components.UsersListScreen
import com.dekow.githubuserinfo.presentation.screen_routes.Screens
import com.dekow.githubuserinfo.ui.theme.BackgroundViolet
import com.dekow.githubuserinfo.ui.theme.GithubUserInfoTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubUserInfoTheme {
                // A surface container using the 'background' color from the theme
              //  window?.statusBarColor = BackgroundViolet.toArgb() //status bar color

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberNavController()

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

//                    UsersListScreen()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GithubUserInfoTheme {

    }
}