package com.githubapp.data.remote.service

import io.reactivex.Observable
import com.githubapp.data.model.RepositoryDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthService {
    @GET("search/repositories")
    fun getData(
        @Query("q") search: String,
    ): Observable<Response<RepositoryDTO>>
}
