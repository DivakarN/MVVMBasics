package com.sysaxiom.mvvmbasics.ui.navigationview.relation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sysaxiom.mvvmbasics.data.repositorys.PrivacyRepository
import com.sysaxiom.mvvmbasics.data.repositorys.RelationRepository
import com.sysaxiom.mvvmbasics.data.repositorys.TermsRepository
import com.sysaxiom.mvvmbasics.ui.navigationview.terms.TermsViewModel

@Suppress("UNCHECKED_CAST")
class RelationViewModelFactory(
    private val repository: RelationRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RelationViewModel(repository) as T
    }
}