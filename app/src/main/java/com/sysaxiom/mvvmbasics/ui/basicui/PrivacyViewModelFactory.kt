package com.sysaxiom.mvvmbasics.ui.basicui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sysaxiom.mvvmbasics.data.repositorys.PrivacyRepository

@Suppress("UNCHECKED_CAST")
class PrivacyViewModelFactory(
    private val repository: PrivacyRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PrivacyViewModel(repository) as T
    }
}