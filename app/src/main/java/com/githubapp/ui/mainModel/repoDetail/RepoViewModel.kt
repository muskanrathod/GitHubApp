package com.githubapp.ui.mainModel.repoDetail

import com.githubapp.base.viewModel.BaseViewModel
import com.githubapp.data.AppDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(appDataManager: AppDataManager) : BaseViewModel(appDataManager) {
}