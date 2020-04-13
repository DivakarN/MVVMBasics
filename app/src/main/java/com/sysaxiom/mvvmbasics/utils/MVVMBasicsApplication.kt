package com.sysaxiom.mvvmbasics.utils

import android.app.Application
import com.sysaxiom.mvvmbasics.data.network.NetworkApis
import com.sysaxiom.mvvmbasics.data.network.NetworkConnectionInterceptor
import com.sysaxiom.mvvmbasics.data.repositorys.AppointmentRepository
import com.sysaxiom.mvvmbasics.handlers.location.LocationHandler
import com.sysaxiom.mvvmbasics.handlers.mqtt.MqttHandler
import com.sysaxiom.mvvmbasics.handlers.network.NetworkHandler
import com.sysaxiom.mvvmbasics.ui.appointment.AppointmentViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMBasicsApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMBasicsApplication))
        bind() from singleton { NetworkHandler(instance()) }
        bind() from singleton { LocationHandler(instance()) }
        bind() from singleton { MqttHandler(instance()) }
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { NetworkApis(instance()) }
        bind() from singleton { AppointmentRepository(instance()) }
        bind() from provider { AppointmentViewModelFactory(instance(),instance()) }

    }

}