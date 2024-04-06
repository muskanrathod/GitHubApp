package com.tookbook.ui.authModel.login

import com.tookbook.base.viewModel.BaseViewModel
import com.tookbook.data.AppDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(appDataManager: AppDataManager): BaseViewModel(appDataManager) {
}