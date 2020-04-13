package com.sysaxiom.mvvmbasics.ui.appointment

interface AppointmentListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}