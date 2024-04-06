package com.tookbook.ui.mainModel

import android.content.Context
import com.tookbook.base.viewModel.BaseViewModel
import com.tookbook.data.AppDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(appDataManager: AppDataManager) :
    BaseViewModel(appDataManager) {
}