package com.sysaxiom.mvvmbasics.data.models

class PrivacyResponse{
    var success : Boolean? = null
    var status : Int? = null
    var message : String? = null
    var data : String? = null

    constructor(success: Boolean?, status: Int?, message: String?, data: String?) {
        this.success = success
        this.status = status
        this.message = message
        this.data = data
    }
}