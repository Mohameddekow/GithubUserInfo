package com.dekow.githubuserinfo.di

import com.dekow.githubuserinfo.commons.Constants
import com.dekow.githubuserinfo.data.remote.AuthInterceptor
import com.dekow.githubuserinfo.data.remote.GithubApi
import com.dekow.githubuserinfo.data.repository.UserRepositoryImpl
import com.dekow.githubuserinfo.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val client = OkHttpClient.Builder().apply {
        addInterceptor(AuthInterceptor())
    }.build()


    @Provides
    @Singleton
    fun provideGithubApi(): GithubApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApi::class.java)
    }


    @Provides
    @Singleton
    fun provideGithubUserRepository(githubApi: GithubApi): UserRepository {
        return UserRepositoryImpl(githubApi)
    }

}