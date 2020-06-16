package com.sysaxiom.mvvmbasics.ui.navigationview.terms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sysaxiom.mvvmbasics.data.repositorys.PrivacyRepository
import com.sysaxiom.mvvmbasics.data.repositorys.TermsRepository

@Suppress("UNCHECKED_CAST")
class TermsViewModelFactory(
    private val repository: TermsRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TermsViewModel(repository) as T
    }
}