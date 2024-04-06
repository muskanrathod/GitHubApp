package com.tookbook.data.pref

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppPreferenceManager @Inject constructor(
    @ApplicationContext private val context: Context,
) : Preferences(), PreferenceSource {
    init {
        init(context, "matricPref")
    }

    var loginStatusString by booleanPref("loginStatus")
    var profileStatusBoolean by booleanPref("profileStatus")

    override fun saveProfileStatus(profileStatus: Boolean) {
        this.profileStatusBoolean = profileStatus
    }

    override fun getProfileStatus(): Boolean {
        return profileStatusBoolean
    }

    override fun saveLoginStatus(loginStatus: Boolean) {
        this.loginStatusString = loginStatus
    }

    override fun getLoginStatus(): Boolean {
        return loginStatusString
    }

}



