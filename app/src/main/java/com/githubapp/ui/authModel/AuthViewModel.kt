package com.githubapp.ui.authModel

import com.githubapp.base.viewModel.BaseViewModel
import com.githubapp.data.AppDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(appDataManager: AppDataManager): BaseViewModel(appDataManager) {
}