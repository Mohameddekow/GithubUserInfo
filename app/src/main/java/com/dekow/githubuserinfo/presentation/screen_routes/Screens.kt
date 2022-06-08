package com.dekow.githubuserinfo.presentation.screen_routes

sealed class Screens(val route: String){
    object UseListScreen : Screens ("user_list_route")
    object UserDetailsScreen : Screens ("user_details_route")
    object UserSearchScreen : Screens ("user_search_route")
}