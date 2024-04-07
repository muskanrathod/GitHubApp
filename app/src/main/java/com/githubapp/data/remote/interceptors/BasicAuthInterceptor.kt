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