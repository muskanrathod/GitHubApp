package com.tookbook.ui.mainModel.home

import com.tookbook.base.viewModel.BaseViewModel
import com.tookbook.data.AppDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(appDataManager: AppDataManager) : BaseViewModel(appDataManager) {
}