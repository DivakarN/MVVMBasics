package com.sysaxiom.mvvmbasics.ui.navigationview.terms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sysaxiom.focozon.model.setting.TermsResponse
import com.sysaxiom.mvvmbasics.data.listeners.LoaderListener
import com.sysaxiom.mvvmbasics.data.repositorys.TermsRepository
import com.sysaxiom.mvvmbasics.utils.ApiException
import com.sysaxiom.mvvmbasics.utils.Coroutines
import com.sysaxiom.mvvmbasics.utils.NoInternetException

class TermsViewModel(
    private val repository: TermsRepository
) : ViewModel() {

    var loaderListener: LoaderListener? = null
    var isFetchNeeded : Boolean = true
    var _termsResponse : MutableLiveData<TermsResponse> = MutableLiveData()
    var termsResponse : LiveData<TermsResponse> = _termsResponse

    fun getTerms() {
        Coroutines.main {
            if(isFetchNeeded.equals(true)){
                loaderListener?.onStarted()
                try {
                    val termsResponse = repository.getTerms()
                    if(!termsResponse.data.isNullOrEmpty()){
                        _termsResponse.postValue(termsResponse)
                        loaderListener?.onSuccess()
                        isFetchNeeded = false
                        return@main
                    }
                    loaderListener?.onFailure(termsResponse.message.toString())
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