package com.dekow.githubuserinfo.ui.theme


import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable


@Composable
fun GithubUserInfoTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        //colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}