package com.tookbook.data.remote

import android.content.Context
import com.tookbook.data.pref.AppPreferenceManager
import com.tookbook.data.remote.service.AuthService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RemoteDataManager @Inject constructor(
    @ApplicationContext val context: Context,
    preferenceManager: AppPreferenceManager,
) : RemoteSource {

    @Inject
    lateinit var authService: AuthService

}
