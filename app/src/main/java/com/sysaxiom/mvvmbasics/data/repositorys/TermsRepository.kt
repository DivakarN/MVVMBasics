package com.sysaxiom.mvvmbasics.data.repositorys

import com.sysaxiom.focozon.model.setting.TermsResponse
import com.sysaxiom.mvvmbasics.data.models.PrivacyResponse
import com.sysaxiom.mvvmbasics.data.models.RelationResponse
import com.sysaxiom.mvvmbasics.data.network.NetworkApis
import com.sysaxiom.mvvmbasics.data.network.SafeApiRequest

class TermsRepository (val networkApis: NetworkApis) : SafeApiRequest() {

    suspend fun getTerms(): TermsResponse {
        return apiRequest { networkApis.getTerms() }
    }

}