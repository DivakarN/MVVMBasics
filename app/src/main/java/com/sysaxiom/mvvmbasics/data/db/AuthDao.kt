package com.sysaxiom.mvvmbasics.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sysaxiom.mvvmbasics.data.db.entities.*

@Dao
interface AuthDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun upsertRelative(relative: List<Relative>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun upsertRelativeMaster(relativeMaster: List<RelativeMaster>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun upsertSpeciality(speciality: List<Speciality>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertUserData(userData: UserData)

    @Query("SELECT * FROM user_data")
    fun getUserData(): LiveData<UserData>

    @Query("SELECT * FROM relative")
    fun getRelative(): LiveData<List<Relative>>

    @Query("SELECT * FROM relative_master")
    fun getRelativeMaster(): LiveData<List<RelativeMaster>>

    @Query("SELECT * FROM speciality")
    fun getSpeciality(): LiveData<List<Speciality>>
}