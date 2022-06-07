package com.dekow.githubuserinfo.presentation.users_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dekow.githubuserinfo.commons.Resource
import com.dekow.githubuserinfo.domain.model.UserDetails
import com.dekow.githubuserinfo.domain.use_case.user.GetUserUseCase
import com.dekow.githubuserinfo.domain.use_case.users_list.GetUsersListUseCase
import com.dekow.githubuserinfo.presentation.user.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class UserListViewModel
    @Inject
    constructor(
        private val getUsersListUseCase: GetUsersListUseCase
    ): ViewModel()
{

    private val _userListState = mutableStateOf(UsersListState())
    val userListState: State<UsersListState> = _userListState

    init {
        getALLUsers()
    }

    private fun getALLUsers() {

        getUsersListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _userListState.value = UsersListState(usersList = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _userListState.value = UsersListState(
                        error = result.errorMessage ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _userListState.value = UsersListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)


    }

}