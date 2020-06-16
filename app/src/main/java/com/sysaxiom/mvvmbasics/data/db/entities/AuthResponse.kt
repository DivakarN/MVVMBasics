package com.sysaxiom.mvvmbasics.data.db.entities

import androidx.room.*

const val CURRENT_USER_ID = 0

data class AuthResponse (
    var success: Boolean = false,
    var status: Int = 0,
    var message: String = "",
    @Embedded
    var data: AuthData? = null
)

data class AuthData (
    var user_data: UserData? = null,
    var relative_master: List<RelativeMaster>? = null,
    var relative: List<Relative>? = null,
    var speciality: List<Speciality>? = null
)

@Entity(tableName = "relative")
data class Relative (
    @PrimaryKey
    var _id: String,
    var updated_at: String,
    var created_at: String,
    var mobile: String,
    var address: String,
    var user_id : String,
    var name: String,
    var email: String,
    var type: String,
    var status: String,
    var __v: Long
)

@Entity(tableName = "relative_master")
data class RelativeMaster (
    @PrimaryKey
    var name: String
)

@Entity(tableName = "speciality")
data class Speciality (
    @PrimaryKey()
    var _id : String,
    var name: String
)

@Entity(tableName = "user_data")
data class UserData (
    var _id: String? = null,
    var updated_at: String? = null,
    var created_at: String? = null,
    var name: String? = null,
    var email: String? = null,
    var mobile: String? = null,
    var lat : String? = null,
    var lon : String?= null,
    var blood: String? =null,
    var insurance_number: String? = null,
    var insurance_company: String? =null,
    var password: String? = null,
    var emergency_contact: String?= null,
    var security_contact: String? = null,
    var push_token: String? = null,
    var status: String? = null,
    var __v: Long? = null
){
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_USER_ID
}


