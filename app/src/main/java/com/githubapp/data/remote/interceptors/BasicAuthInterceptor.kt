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