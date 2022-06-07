package com.dekow.githubuserinfo.data.remote




import com.dekow.githubuserinfo.data.dto.UserDto
import com.dekow.githubuserinfo.data.dto.UsersListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("/users/{userName}")
    suspend fun getUser(
        @Path("userName") userName: String
    ):UserDto


    @GET("/users/{userName}/followers")
    suspend fun getUserFollowers(
        @Path("userName") userName: String
    ):List<UserDto>


    @GET("/users/{userName}/following")
    suspend fun getUserFollowing(
        @Path("userName") userName: String
    ):List<UserDto>



    @GET("/users")
    suspend fun getAllUsers():List<UsersListDto>

}