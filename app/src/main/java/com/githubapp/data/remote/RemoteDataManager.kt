package com.githubapp.data.remote

import android.content.Context
import com.githubapp.data.model.RepositoryDTO
import com.githubapp.data.pref.AppPreferenceManager
import com.githubapp.data.remote.service.AuthService
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class RemoteDataManager @Inject constructor(
    @ApplicationContext val context: Context,
    preferenceManager: AppPreferenceManager,
) : RemoteSource {

    @Inject
    lateinit var authService: AuthService
    override fun getData(search: String): Observable<Response<RepositoryDTO>> =
        authService.getData(search)


}
