package com.dekow.githubuserinfo.data.repository


import com.dekow.githubuserinfo.data.dto.UsersListDto
import com.dekow.githubuserinfo.data.remote.GithubApi
import com.dekow.githubuserinfo.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl
    @Inject
    constructor(
        private val githubApi: GithubApi
    ): UserRepository
{

    override suspend fun getAllUserTest(): List<UsersListDto> {

        return githubApi.getAllUsers()

    }

}