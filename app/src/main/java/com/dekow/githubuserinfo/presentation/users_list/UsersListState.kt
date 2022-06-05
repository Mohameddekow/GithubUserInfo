package com.dekow.githubuserinfo.presentation.users_list

import com.dekow.githubuserinfo.domain.model.User

data class UsersListState(
    val isLoading: Boolean = false,
    val usersList: List<User> = emptyList(),
    val error: String = ""
)