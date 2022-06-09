package com.dekow.githubuserinfo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dekow.githubuserinfo.presentation.screen_routes.Screens
import com.dekow.githubuserinfo.ui.theme.BackgroundViolet
@Composable
fun SearchUserScreen(
    navController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundViolet)
            .padding(horizontal = 15.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        var userName by remember { mutableStateOf("")}

        OutlinedTextField(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            value = userName,
            onValueChange = {
                userName = it
            },
            placeholder = {
                Text(
                    modifier = Modifier,
                        //.alpha(ContentAlpha.medium),
                    text = "Search user here...",
                    color = Color.Black
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
//                    modifier = Modifier
//                        .alpha(ContentAlpha.medium),
                    onClick = {
                        //search a user
                        navController.navigate(
                            (Screens.UserDetailsScreen.route + "/$userName")
                        )

                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "search icon",
                        tint = Color.Black
                    )
                }
            } ,
            trailingIcon = {
                IconButton(
                    onClick = {
                        //back to list screen
                        userName = ""
                        navController.navigate(Screens.UseListScreen.route) {
                            popUpTo(Screens.UseListScreen.route) {
                                inclusive = true
                            }
                        }

                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "close icon",
                        tint = Color.Black
                    )
                }
            },
//            colors = TextFieldDefaults.textFieldColors(
//                backgroundColor = Color.Transparent,
//                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
//            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    //search a user
                    navController.navigate(
                        (Screens.UserDetailsScreen.route + "/$userName")
                    )
                }
            )

        )
    }
}



@Preview()
@Composable
fun SearchUserScreenPre() {
    SearchUserScreen(navController = rememberNavController())

}