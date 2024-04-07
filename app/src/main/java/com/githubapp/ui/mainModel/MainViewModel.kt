package com.githubapp.ui.mainModel

import com.githubapp.base.viewModel.BaseViewModel
import com.githubapp.data.AppDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(appDataManager: AppDataManager) :
    BaseViewModel(appDataManager) {
}