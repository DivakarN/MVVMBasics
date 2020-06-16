package com.sysaxiom.mvvmbasics.ui.recyclerview

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sysaxiom.mvvmbasics.data.models.AppointmentResponse
import com.sysaxiom.mvvmbasics.data.listeners.LoaderListener
import com.sysaxiom.mvvmbasics.data.repositorys.AppointmentRepository
import com.sysaxiom.mvvmbasics.handlers.location.LocationHandler
import com.sysaxiom.mvvmbasics.handlers.mqtt.MqttHandler
import com.sysaxiom.mvvmbasics.handlers.network.NetworkHandler
import com.sysaxiom.mvvmbasics.utils.ApiException
import com.sysaxiom.mvvmbasics.utils.Coroutines
import com.sysaxiom.mvvmbasics.utils.NoInternetException

class AppointmentViewModel(
    private val repository: AppointmentRepository,
    private val context: Context
) : ViewModel() {

    var loaderListener: LoaderListener? = null
    var isFetchNeeded : Boolean = true
    var _appointmentResponse : MutableLiveData<AppointmentResponse> = MutableLiveData()
    var appointmentResponse : LiveData<AppointmentResponse> = _appointmentResponse

    fun getNetwork() = NetworkHandler(context)

    fun getLocation() = LocationHandler(context)

    fun getMqtt() = MqttHandler(context)

    fun getAppointment() {
        Coroutines.main {
            if(isFetchNeeded.equals(true)){
                loaderListener?.onStarted()
                try {
                    val appointmentResponse = repository.getAppointment("5e8387c7799ce5678810639b")
                    if(!appointmentResponse.data.isNullOrEmpty()){
                        _appointmentResponse.postValue(appointmentResponse)
                        loaderListener?.onSuccess()
                        isFetchNeeded = false
                        return@main
                    }
                    loaderListener?.onFailure(appointmentResponse.message)
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