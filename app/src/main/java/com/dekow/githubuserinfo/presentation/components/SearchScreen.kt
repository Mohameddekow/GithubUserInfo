package com.dekow.githubuserinfo.presentation.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchUserScreen() {
    Scaffold(
        topBar = {
            DefaultAppBar (
                onSearchClicked = {}
            )
        }
    ) {

    }
}

@Composable
fun DefaultAppBar(
    onSearchClicked: () -> Unit
) {

    TopAppBar(
        title = {
            Text(text = "Home")

        },
        actions = {
            IconButton(onClick = { onSearchClicked() }) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search",
                    tint = Color.White,
                )
            }
        }
    )

}




@Preview
@Composable
fun SearchUserScreenPre() {
    SearchUserScreen()
}