package com.githubapp.data.remote.interceptors

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient

class BasicAuthInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()

        request = request.newBuilder().header("Authorization", "Bearer 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").build()

        return chain.proceed(request)
    }
}