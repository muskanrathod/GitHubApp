package com.githubapp.data.remote

import com.githubapp.data.model.RepositoryDTO
import io.reactivex.Observable
import retrofit2.Response


interface RemoteSource {
    fun getData(search: String): Observable<Response<RepositoryDTO>>
}