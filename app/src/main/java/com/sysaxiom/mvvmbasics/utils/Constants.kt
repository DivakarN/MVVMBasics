package com.sysaxiom.mvvmbasics.utils

object UrlsFields {

    const val BaseURL = "https://spotrush.in/api/v1/"
    const val ID = "id"
    const val MOBILE = "mobile"
    const val PASSWORD = "password"
    const val GET_APPOINTMENT = "patient/getAppointment"
    const val GET_PRIVACY = "ambulance/getPrivacy"
    const val GET_TERMS = "ambulance/getTerms"
    const val GET_RELATION ="patient/getRelation"
    const val LOGIN = "patient/login"
    const val MAXIMUM_TIMEOUT = 90
    const val NO_INTERNET_CONNECTTION = "No Internet Connecttion"
    const val MQTT_DEV : String = "wss://spotrush.in:8084/mqtt"
    const val MQTT_DEV_USERNAME : String = "admin"
    const val MQTT_DEV_PASSWORD : String ="public"
    const val MQTT_DEV_CLIENT_ID : String ="web_android_spot"
}

fun returnMqttTopicNames(): ArrayList<String> {
    val mqttTopicName = ArrayList<String>()
    mqttTopicName.add("hello_world")
    return mqttTopicName
}
