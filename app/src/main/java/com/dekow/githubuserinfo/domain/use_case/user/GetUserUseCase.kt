package com.dekow.githubuserinfo.domain.use_case.user

import com.dekow.githubuserinfo.commons.Resource
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
import java.lang.Exception
import javax.inject.Inject


class GetUserUseCase
@Inject
constructor(
    private val repository: UserRepository
) {
    operator fun invoke(userName: String): Flow<Resource<UserDetails>> = flow {

        try {
            emit(Resource.Loading<UserDetails>())

            val user = repository.getUser(userName).toUserDetails()
            emit(Resource.Success<UserDetails>(user))

        }catch (e: HttpException){
            emit(Resource.Error<UserDetails>(e.localizedMessage ?: "An unexpected error occurred"))

        }catch (e: IOException){
            emit(Resource.Error<UserDetails>("Couldn't reach server. Check your internet connection."))
        }catch (e: Exception){
            emit(Resource.Error<UserDetails>(e.localizedMessage ?: "An unexpected error occurred"))
        }

    }
}