package com.dekow.githubuserinfo.presentation.user

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dekow.githubuserinfo.commons.Constants.FOLLOWERS
import com.dekow.githubuserinfo.commons.Constants.USER_NAME
import com.dekow.githubuserinfo.commons.Resource
import com.dekow.githubuserinfo.domain.model.UserDetails
import com.dekow.githubuserinfo.domain.use_case.followers.GetFollowersUseCase
import com.dekow.githubuserinfo.domain.use_case.following.GetFollowingUseCase
import com.dekow.githubuserinfo.domain.use_case.user.GetUserUseCase
import com.dekow.githubuserinfo.domain.use_case.users_list.GetUsersListUseCase
import com.dekow.githubuserinfo.presentation.user.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UsersViewModel
    @Inject
    constructor(
        private val getUserUseCase: GetUserUseCase,
        private val getFollowersUseCase: GetFollowersUseCase,
        private val getFollowingUseCase: GetFollowingUseCase,
        savedStateHandle: SavedStateHandle
    ): ViewModel()
{

    private val _userState = mutableStateOf(UserState())
    val userState: State<UserState> = _userState

    private val _followersState = mutableStateOf(UserFollowsState())
    val followersState: State<UserFollowsState> = _followersState

    private val _followingState = mutableStateOf(UserFollowsState())
    val followingState: State<UserFollowsState> = _followingState

    init {

        savedStateHandle.get<String>(USER_NAME)?.let { userName ->
            getUserFollowing(userName)

            getUser(userName)

            getUserFollowers(userName)

        }

    }

    private fun getUser(userName: String) {
        getUserUseCase(userName).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _userState.value = UserState(user = result.data)
                }
                is Resource.Error -> {
                    _userState.value = UserState(
                        error = result.errorMessage ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _userState.value = UserState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }




    private fun getUserFollowers(userName: String) {
        getFollowersUseCase(userName).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _followersState.value = UserFollowsState(userFollows = result.data ?: emptyList() )
                }
                is Resource.Error -> {
                    _followersState.value = UserFollowsState(
                        error = result.errorMessage ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _followersState.value = UserFollowsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

    private fun getUserFollowing(userName: String) {

        getFollowingUseCase(userName).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _followingState.value = UserFollowsState(userFollows = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _followingState.value = UserFollowsState(
                        error = result.errorMessage ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _followingState.value = UserFollowsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)


    }


}