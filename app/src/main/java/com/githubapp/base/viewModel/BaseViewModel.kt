package com.githubapp.base.viewModel

import androidx.lifecycle.ViewModel
import com.githubapp.data.AppDataManager

abstract class BaseViewModel(open val appDataManager: AppDataManager) : ViewModel() {

    protected var TAG: String = this.javaClass.simpleName

}