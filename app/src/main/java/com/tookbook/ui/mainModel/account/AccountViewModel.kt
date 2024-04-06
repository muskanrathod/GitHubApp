package com.tookbook.ui.mainModel.account

import com.tookbook.base.viewModel.BaseViewModel
import com.tookbook.data.AppDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(appDataManager: AppDataManager) : BaseViewModel(appDataManager) {
}