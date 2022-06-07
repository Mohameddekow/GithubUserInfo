package com.dekow.githubuserinfo.presentation.user

import com.dekow.githubuserinfo.domain.model.User
import com.dekow.githubuserinfo.domain.model.UserDetails

data class UserFollowsState(
    val isLoading: Boolean = false,
    val userFollows: List<UserDetails> = emptyList(),
    val error: String = ""
)