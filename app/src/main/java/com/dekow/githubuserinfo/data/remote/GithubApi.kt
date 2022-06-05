package com.dekow.githubuserinfo.data.remote




import com.dekow.githubuserinfo.data.dto.UsersListDto
import retrofit2.http.GET

interface GithubApi {

    @GET("/users?q=language:kotlin+location\"nairobi\"")
    suspend fun getAllUsers():List<UsersListDto>

}