package com.dekow.githubuserinfo.presentation.components


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dekow.githubuserinfo.domain.model.User
import com.dekow.githubuserinfo.presentation.users_list.UserViewModel



@Composable
fun UsersListScreen(
    viewModel: UserViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Log.d("userTest", state.usersList.toString())

    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.usersList) { user ->
                UserItem(
                    user = user,
                )
            }
        }

        if(state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}


@Composable
fun UserItem(
    user: User
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = user.name,
            modifier = Modifier
                .padding(10.dp),
            fontSize = 24.sp
        )
        Text(
            text = user.location,
            modifier = Modifier
                .padding(10.dp),
            fontSize = 24.sp
        )
    }
}