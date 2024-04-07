package com.githubapp.data.pref

import com.githubapp.data.model.RepositoryDTO

interface PreferenceSource {

    fun saveRepoDTO(key: String, value: String?)
    fun getRepoDTO(key: String): String?
}