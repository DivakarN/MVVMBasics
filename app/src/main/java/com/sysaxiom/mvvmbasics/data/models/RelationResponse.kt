package com.sysaxiom.mvvmbasics.data.models

import com.sysaxiom.mvvmbasics.data.db.entities.Relative

data class RelationResponse (
    val success: Boolean,
    val status: Long,
    val message: String,
    val data: List<Relative>
)
