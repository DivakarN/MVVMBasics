package com.sysaxiom.mvvmbasics.ui.room

import androidx.lifecycle.ViewModel
import com.sysaxiom.mvvmbasics.data.listeners.LoaderListener
import com.sysaxiom.mvvmbasics.data.repositorys.AuthRepository
import com.sysaxiom.mvvmbasics.utils.ApiException
import com.sysaxiom.mvvmbasics.utils.Coroutines
import com.sysaxiom.mvvmbasics.utils.NoInternetException

class LoginViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    var loaderListener: LoaderListener? = null
    var isFetchNeeded : Boolean = true

    fun getUserData() = repository.getUserData()

    fun getRelative() = repository.getRelative()

    fun getRelativeMaster() = repository.getRelativeMaster()

    fun getSpeciality() = repository.getSpeciality()

    fun login() {
        Coroutines.main {
            if(isFetchNeeded.equals(true)){
                loaderListener?.onStarted()
                try {
                    val authResponse = repository.login("7200604540","12345678")
                    if(authResponse.success.equals(true)){
                        loaderListener?.onSuccess()
                        isFetchNeeded = false
                        if(!authResponse.data?.relative.isNullOrEmpty()){
                            repository.upsertRelative(authResponse.data?.relative!!)
                        }
                        if(!authResponse.data?.relative_master.isNullOrEmpty()){
                            repository.upsertRelativeMaster(authResponse.data?.relative_master!!)
                        }
                        if(!authResponse.data?.speciality.isNullOrEmpty()){
                            repository.upsertSpeciality(authResponse.data?.speciality!!)
                        }
                        if(authResponse.data?.user_data != null){
                            repository.upsertUserData(authResponse.data?.user_data!!)
                        }
                        return@main
                    }
                    loaderListener?.onFailure(authResponse.message)
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