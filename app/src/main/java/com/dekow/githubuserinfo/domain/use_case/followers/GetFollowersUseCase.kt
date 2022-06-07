package com.dekow.githubuserinfo.domain.use_case.followers

import com.dekow.githubuserinfo.commons.Resource
import com.dekow.githubuserinfo.data.dto.UserDto
import com.dekow.githubuserinfo.data.dto.toUser
import com.dekow.githubuserinfo.data.dto.toUserDetails
import com.dekow.githubuserinfo.domain.model.*
import com.dekow.githubuserinfo.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetFollowersUseCase
@Inject
constructor(
    private val repository: UserRepository
) {
    operator fun invoke(userName: String): Flow<Resource<List<UserDetails>>> = flow {

        try {
            emit(Resource.Loading<List<UserDetails>>())

            val usersFollowsList = repository.getUserFollowers(userName).map { it.toUserDetails() }
            emit(Resource.Success<List<UserDetails>>(usersFollowsList))

        }catch (e: HttpException){
            emit(Resource.Error<List<UserDetails>>(e.localizedMessage ?: "An unexpected error occurred"))

        }catch (e: IOException){
            emit(Resource.Error<List<UserDetails>>("Couldn't reach server. Check your internet connection."))
        }

    }
}