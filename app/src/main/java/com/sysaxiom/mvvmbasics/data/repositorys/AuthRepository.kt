package com.sysaxiom.mvvmbasics.data.repositorys

import com.sysaxiom.mvvmbasics.data.db.MVVMBasicsDatabase
import com.sysaxiom.mvvmbasics.data.db.entities.*
import com.sysaxiom.mvvmbasics.data.network.NetworkApis
import com.sysaxiom.mvvmbasics.data.network.SafeApiRequest

class AuthRepository(
    private val db: MVVMBasicsDatabase,
    private val networkApis: NetworkApis
) : SafeApiRequest() {

    suspend fun upsertRelative(relative: List<Relative>) = db.getAuthDao().upsertRelative(relative)

    suspend fun upsertRelativeMaster(relativeMaster: List<RelativeMaster>) = db.getAuthDao().upsertRelativeMaster(relativeMaster)

    suspend fun upsertSpeciality(speciality : List<Speciality>) = db.getAuthDao().upsertSpeciality(speciality)

    suspend fun upsertUserData(userData: UserData) = db.getAuthDao().upsertUserData(userData)

    fun getUserData() = db.getAuthDao().getUserData()

    fun getRelative() = db.getAuthDao().getRelative()

    fun getRelativeMaster() = db.getAuthDao().getRelativeMaster()

    fun getSpeciality() = db.getAuthDao().getSpeciality()

    suspend fun login(mobile : String , password : String): AuthResponse {
        return apiRequest { networkApis.login(mobile,password) }
    }
}