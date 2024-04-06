package com.tookbook.ui.mainModel.bookDetail

import com.tookbook.base.viewModel.BaseViewModel
import com.tookbook.data.AppDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(appDataManager: AppDataManager) : BaseViewModel(appDataManager) {
}