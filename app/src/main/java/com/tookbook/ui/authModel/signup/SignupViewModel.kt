package com.tookbook.ui.authModel.signup

import com.tookbook.base.viewModel.BaseViewModel
import com.tookbook.data.AppDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(appDataManager: AppDataManager): BaseViewModel(appDataManager) {
}