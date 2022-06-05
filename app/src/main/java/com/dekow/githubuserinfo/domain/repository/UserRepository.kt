package com.dekow.githubuserinfo.domain.repository


import com.dekow.githubuserinfo.data.dto.UsersListDto

interface UserRepository {

    suspend fun getAllUserTest(): List<UsersListDto>

}