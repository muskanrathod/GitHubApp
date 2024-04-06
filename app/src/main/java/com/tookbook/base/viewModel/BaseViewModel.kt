package com.tookbook.base.viewModel

import androidx.lifecycle.ViewModel
import com.tookbook.data.AppDataManager

abstract class BaseViewModel(open val appDataManager: AppDataManager) : ViewModel() {

    protected var TAG: String = this.javaClass.simpleName

}