package com.dekow.githubuserinfo.data.repository


import com.dekow.githubuserinfo.data.dto.UserDto
import com.dekow.githubuserinfo.data.dto.UsersListDto
import com.dekow.githubuserinfo.data.remote.GithubApi
import com.dekow.githubuserinfo.domain.model.UserDetails
import com.dekow.githubuserinfo.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl
    @Inject
    constructor(
        private val githubApi: GithubApi
    ): UserRepository
{

    override suspend fun getUser(userName: String): UserDto {
        return githubApi.getUser(userName)
    }

    override suspend fun getUserFollowers(userName: String): List<UserDto> {
        return githubApi.getUserFollowers(userName)
    }

    override suspend fun getUserFollowing(userName: String): List<UserDto> {
        return githubApi.getUserFollowing(userName)
    }


    override suspend fun getAllUserTest(): List<UsersListDto> {

        return githubApi.getAllUsers()

    }

}