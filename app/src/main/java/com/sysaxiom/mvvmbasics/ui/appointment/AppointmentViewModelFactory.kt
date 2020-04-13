package com.sysaxiom.mvvmbasics.ui.appointment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sysaxiom.mvvmbasics.data.repositorys.AppointmentRepository

@Suppress("UNCHECKED_CAST")
class AppointmentViewModelFactory(
    private val repository: AppointmentRepository,
    private val context: Context
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AppointmentViewModel(repository,context) as T
    }
}