package com.sysaxiom.mvvmbasics.data.datasources

import com.sysaxiom.mvvmbasics.data.network.NetworkApis

class AppointmentDataSource ( private val api: NetworkApis)  {

    suspend fun getAppointment() = api.getAppointment("5e8387c7799ce5678810639b")

}