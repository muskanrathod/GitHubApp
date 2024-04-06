package com.tookbook.data.pref

interface PreferenceSource {

    fun saveProfileStatus(profileStatus: Boolean)
    fun getProfileStatus(): Boolean

    fun saveLoginStatus(loginStatus: Boolean)
    fun getLoginStatus(): Boolean
}