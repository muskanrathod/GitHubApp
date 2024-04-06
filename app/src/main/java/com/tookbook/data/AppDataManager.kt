package com.tookbook.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.tookbook.data.pref.AppPreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppDataManager @Inject constructor(
    @ApplicationContext val context: Context,
    preferenceManager: AppPreferenceManager
) : DataManager {

    var observer = MutableLiveData<Boolean>()
    public var appPreferenceManager: AppPreferenceManager = preferenceManager

    override fun saveProfileStatus(loginToken: Boolean) = appPreferenceManager.saveProfileStatus(loginToken)

    override fun getProfileStatus(): Boolean = appPreferenceManager.getProfileStatus()

    override fun saveLoginStatus(loginToken: Boolean) = appPreferenceManager.saveLoginStatus(loginToken)

    override fun getLoginStatus(): Boolean = appPreferenceManager.getLoginStatus()

}