package com.sysaxiom.mvvmbasics.data.listeners

interface LoaderListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}