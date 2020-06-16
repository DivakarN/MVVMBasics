package com.sysaxiom.mvvmbasics.data.network

import com.sysaxiom.focozon.model.setting.TermsResponse
import com.sysaxiom.mvvmbasics.data.models.PrivacyResponse
import com.sysaxiom.mvvmbasics.data.models.AppointmentResponse
import com.sysaxiom.mvvmbasics.data.db.entities.AuthResponse
import com.sysaxiom.mvvmbasics.data.models.RelationResponse
import com.sysaxiom.mvvmbasics.utils.UrlsFields
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface NetworkApis {

    @GET(UrlsFields.GET_APPOINTMENT)
    suspend fun getAppointment(
        @Query(UrlsFields.ID) id: String
    ) : Response<AppointmentResponse>

    @GET(UrlsFields.GET_PRIVACY)
    suspend fun getPrivacy() : Response<PrivacyResponse>

    @GET(UrlsFields.GET_TERMS)
    suspend fun getTerms() : Response<TermsResponse>

    @POST(UrlsFields.GET_RELATION)
    @FormUrlEncoded
    suspend fun getRelation(
        @Field(UrlsFields.ID) id: String
    ) : Response<RelationResponse>

    @POST(UrlsFields.LOGIN)
    @FormUrlEncoded
    suspend fun login(
        @Field(UrlsFields.MOBILE) mobile: String,
        @Field(UrlsFields.PASSWORD) password : String
    ) : Response<AuthResponse>

    companion object{

        operator fun invoke( networkConnectionInterceptor: NetworkConnectionInterceptor) : NetworkApis {

            val okHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .connectTimeout(UrlsFields.MAXIMUM_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(UrlsFields.MAXIMUM_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(UrlsFields.MAXIMUM_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(UrlsFields.BaseURL)
                .client(okHttpclient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkApis::class.java)

        }
    }
}