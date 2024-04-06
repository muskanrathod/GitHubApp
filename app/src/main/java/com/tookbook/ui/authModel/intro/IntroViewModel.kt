package com.tookbook.ui.authModel.intro

import com.tookbook.base.viewModel.BaseViewModel
import com.tookbook.data.AppDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(appDataManager: AppDataManager): BaseViewModel(appDataManager) {
}