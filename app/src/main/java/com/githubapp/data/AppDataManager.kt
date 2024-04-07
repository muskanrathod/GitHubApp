package com.githubapp.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.githubapp.data.model.RepositoryDTO
import com.githubapp.data.pref.AppPreferenceManager
import com.githubapp.data.remote.RemoteDataManager
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class AppDataManager @Inject constructor(
    @ApplicationContext val context: Context,
    preferenceManager: AppPreferenceManager,
    private val remoteDataManager: RemoteDataManager
) : DataManager {

    var observer = MutableLiveData<Boolean>()
    public var appPreferenceManager: AppPreferenceManager = preferenceManager

    override fun saveRepoDTO(key: String, value: String?) = appPreferenceManager.saveRepoDTO(key, value)

    override fun getRepoDTO(key: String): String? = appPreferenceManager.getRepoDTO(key)

    override fun getData(search: String): Observable<Response<RepositoryDTO>> = remoteDataManager.getData(search)


}