package com.dekow.githubuserinfo.presentation.user

import com.dekow.githubuserinfo.domain.model.User
import com.dekow.githubuserinfo.domain.model.UserDetails

data class UserState(
    val isLoading: Boolean = false,
    val user: UserDetails? = null,
    val error: String = ""
)