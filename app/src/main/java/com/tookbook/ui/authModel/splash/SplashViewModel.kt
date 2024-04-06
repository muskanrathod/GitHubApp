package com.tookbook.ui.authModel.splash

import com.tookbook.base.viewModel.BaseViewModel
import com.tookbook.data.AppDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(appDataManager: AppDataManager): BaseViewModel(appDataManager) {
}