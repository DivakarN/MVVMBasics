package com.sysaxiom.mvvmbasics.ui.room

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sysaxiom.mvvmbasics.data.repositorys.AppointmentRepository
import com.sysaxiom.mvvmbasics.data.repositorys.AuthRepository

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(
    private val repository: AuthRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}