package com.dekow.githubuserinfo.domain.repository


import com.dekow.githubuserinfo.data.dto.UserDto
import com.dekow.githubuserinfo.data.dto.UsersListDto
import com.dekow.githubuserinfo.domain.model.UserDetails

interface UserRepository {

    suspend fun getUser(userName: String): UserDto

    suspend fun getUserFollowers(userName: String): List<UserDto>

    suspend fun getUserFollowing(userName: String): List<UserDto>

    suspend fun getAllUserTest(): List<UsersListDto>

}