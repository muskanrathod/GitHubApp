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