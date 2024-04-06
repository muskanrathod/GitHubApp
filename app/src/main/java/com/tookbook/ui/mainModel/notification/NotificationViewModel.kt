package com.tookbook.ui.mainModel.notification

import com.tookbook.base.viewModel.BaseViewModel
import com.tookbook.data.AppDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(appDataManager: AppDataManager) : BaseViewModel(appDataManager) {
}