package com.githubapp.data.pref

import android.content.Context
import com.githubapp.data.model.RepositoryDTO
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppPreferenceManager @Inject constructor(
    @ApplicationContext private val context: Context,
) : Preferences(), PreferenceSource {
    init {
        init(context, "AppPref")
    }

    override fun saveRepoDTO(key: String, value: String?) {
        var dataStoreKey by stringPref( key )
        dataStoreKey= value
    }

    override fun getRepoDTO(key: String): String? {
        val dataStoreKey by stringPref( key )
        return  dataStoreKey
    }

}



