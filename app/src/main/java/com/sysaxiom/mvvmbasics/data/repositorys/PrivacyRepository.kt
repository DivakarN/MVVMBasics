package com.sysaxiom.mvvmbasics.data.repositorys

import com.sysaxiom.mvvmbasics.data.models.PrivacyResponse
import com.sysaxiom.mvvmbasics.data.network.NetworkApis
import com.sysaxiom.mvvmbasics.data.network.SafeApiRequest

class PrivacyRepository (val networkApis: NetworkApis) : SafeApiRequest() {

    suspend fun getPrivacy(): PrivacyResponse {
        return apiRequest { networkApis.getPrivacy() }
    }

}