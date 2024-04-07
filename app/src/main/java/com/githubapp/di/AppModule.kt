package com.githubapp.di

import android.app.Application
import android.content.Context
import com.githubapp.data.remote.interceptors.BasicAuthInterceptor
import com.githubapp.data.remote.service.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    internal fun bindContext(application: Application): Context = application

    @Singleton
    @Provides
    fun provideAuthService(@ApplicationContext context: Context): AuthService {
        val mContext = context

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttp =OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor())

        okHttp.addInterceptor(loggingInterceptor)
        okHttp.connectTimeout(30, TimeUnit.SECONDS)
        okHttp.readTimeout(30, TimeUnit.SECONDS)
        okHttp.writeTimeout(30, TimeUnit.SECONDS)
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            //.addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(okHttp.build())
            .build()
            .create(AuthService::class.java)
    }

}