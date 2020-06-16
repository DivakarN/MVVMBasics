package com.sysaxiom.mvvmbasics.data.repositorys

import com.sysaxiom.focozon.model.setting.TermsResponse
import com.sysaxiom.mvvmbasics.data.models.PrivacyResponse
import com.sysaxiom.mvvmbasics.data.models.RelationResponse
import com.sysaxiom.mvvmbasics.data.network.NetworkApis
import com.sysaxiom.mvvmbasics.data.network.SafeApiRequest

class RelationRepository (val networkApis: NetworkApis) : SafeApiRequest() {

    suspend fun getRelation(id: String): RelationResponse {
        return apiRequest { networkApis.getRelation(id) }
    }

}