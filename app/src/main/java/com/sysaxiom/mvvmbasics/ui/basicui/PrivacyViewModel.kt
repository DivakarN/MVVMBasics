package com.sysaxiom.mvvmbasics.ui.basicui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sysaxiom.mvvmbasics.data.models.PrivacyResponse
import com.sysaxiom.mvvmbasics.data.listeners.LoaderListener
import com.sysaxiom.mvvmbasics.data.repositorys.PrivacyRepository
import com.sysaxiom.mvvmbasics.utils.ApiException
import com.sysaxiom.mvvmbasics.utils.Coroutines
import com.sysaxiom.mvvmbasics.utils.NoInternetException

class PrivacyViewModel(
    private val repository: PrivacyRepository
) : ViewModel() {

    var loaderListener: LoaderListener? = null
    var isFetchNeeded : Boolean = true
    var _privacyResponse : MutableLiveData<PrivacyResponse> = MutableLiveData()
    var privacyResponse : LiveData<PrivacyResponse> = _privacyResponse

    fun getPrivacy() {
        Coroutines.main {
            if(isFetchNeeded.equals(true)){
                loaderListener?.onStarted()
                try {
                    val privacyResponse = repository.getPrivacy()
                    if(!privacyResponse.data.isNullOrEmpty()){
                        _privacyResponse.postValue(privacyResponse)
                        loaderListener?.onSuccess()
                        isFetchNeeded = false
                        return@main
                    }
                    loaderListener?.onFailure(privacyResponse.message.toString())
                }catch(e: ApiException){
                    loaderListener?.onFailure(e.message.toString())
                }catch (e: NoInternetException){
                    loaderListener?.onFailure(e.message.toString())
                }catch (e : Exception){
                    loaderListener?.onFailure(e.message.toString())
                }
            }
        }
    }

}