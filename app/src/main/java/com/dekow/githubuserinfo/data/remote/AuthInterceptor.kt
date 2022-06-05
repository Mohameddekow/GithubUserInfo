package com.dekow.githubuserinfo.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("AuthToken", "Auth-Key")
            .build()

        return  chain.proceed(request = request)
    }

}