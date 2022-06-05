package com.dekow.githubuserinfo.presentation.users_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dekow.githubuserinfo.commons.Resource
import com.dekow.githubuserinfo.domain.use_case.users_list.GetUsersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel
    @Inject
    constructor(
        private val getUsersListUseCase: GetUsersListUseCase
    ): ViewModel()
{
    private val _state = mutableStateOf(UsersListState())
    val state: State<UsersListState> = _state

    init {
        getALLUsers()
    }


    private fun getALLUsers() {

        getUsersListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = UsersListState(usersList = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = UsersListState(
                        error = result.errorMessage ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = UsersListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)


//        viewModelScope.launch (Dispatchers.IO){
//
//            getUsersListUseCase().onEach { result ->
//                when (result) {
//                    is Resource.Success -> {
//                        _state.value = UsersListState(usersList = result.data ?: emptyList())
//                    }
//                    is Resource.Error -> {
//                        _state.value = UsersListState(
//                            error = result.errorMessage ?: "An unexpected error occurred"
//                        )
//                    }
//                    is Resource.Loading -> {
//                        _state.value = UsersListState(isLoading = true)
//                    }
//                }
//            }.launchIn(viewModelScope)
//
//        }



    }

}