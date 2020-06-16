package com.sysaxiom.mvvmbasics.ui.navigationview.relation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sysaxiom.mvvmbasics.data.listeners.LoaderListener
import com.sysaxiom.mvvmbasics.data.models.RelationResponse
import com.sysaxiom.mvvmbasics.data.repositorys.RelationRepository
import com.sysaxiom.mvvmbasics.utils.ApiException
import com.sysaxiom.mvvmbasics.utils.Coroutines
import com.sysaxiom.mvvmbasics.utils.NoInternetException

class RelationViewModel(
    private val repository: RelationRepository
) : ViewModel() {

    var loaderListener: LoaderListener? = null
    var isFetchNeeded : Boolean = true
    var _relationResponse : MutableLiveData<RelationResponse> = MutableLiveData()
    var relationResponse : LiveData<RelationResponse> = _relationResponse

    fun getRelation() {
        Coroutines.main {
            if(isFetchNeeded.equals(true)){
                loaderListener?.onStarted()
                try {
                    val relationResponse = repository.getRelation("5e7892e5e9e7a7287f2e5bcb")
                    if(!relationResponse.data.isNullOrEmpty()){
                        _relationResponse.postValue(relationResponse)
                        loaderListener?.onSuccess()
                        isFetchNeeded = false
                        return@main
                    }
                    loaderListener?.onFailure(relationResponse.message.toString())
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