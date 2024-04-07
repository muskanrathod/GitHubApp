package com.githubapp.ui.authModel.splash

import com.githubapp.base.viewModel.BaseViewModel
import com.githubapp.data.AppDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(appDataManager: AppDataManager): BaseViewModel(appDataManager) {
}