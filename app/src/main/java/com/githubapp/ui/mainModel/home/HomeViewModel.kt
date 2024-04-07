package com.githubapp.ui.mainModel.home

import androidx.lifecycle.MutableLiveData
import com.githubapp.base.viewModel.BaseViewModel
import com.githubapp.data.AppDataManager
import com.githubapp.data.model.RepositoryDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(appDataManager: AppDataManager) : BaseViewModel(appDataManager) {

    protected var compositeDisposable = CompositeDisposable()

    val successData = MutableLiveData<RepositoryDTO>()
    val message = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    
    fun getData(
        search: String,
    ) {
        loading.value = true

        compositeDisposable.add(
            appDataManager.getData(search)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe({ response ->
                    loading.value = false
                    if (response.code() == 200) {

                        val data = response.body()

                        if (data != null){
                            successData.value = response.body()
//                            Log.d(TAG, "login: ${response.body()}")

//                            appDataManager.saveCsrfToken(BaseInterface.CSRF_TOKEN, loginResponse.csrfToken)
//                            appDataManager.saveLogoutToken(BaseInterface.LOGOUT_TOKEN, loginResponse.logoutToken)
//                            appDataManager.saveId(BaseInterface.ID, loginResponse.currentUser!!.uid!!.toInt())
                            
                        }


                    } else {
                        val jsonObject = JSONObject(response.errorBody()!!.string())
                        val errorMessage: String = jsonObject.getString("message")
                        message.value = errorMessage
                    }
                }, {
                    loading.value = false
                    it.printStackTrace()
                })
        )
    }

}