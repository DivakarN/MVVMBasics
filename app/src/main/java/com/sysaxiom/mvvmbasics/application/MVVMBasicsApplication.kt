package com.sysaxiom.mvvmbasics.application

import android.app.Application
import com.sysaxiom.mvvmbasics.data.db.MVVMBasicsDatabase
import com.sysaxiom.mvvmbasics.data.network.NetworkApis
import com.sysaxiom.mvvmbasics.data.network.NetworkConnectionInterceptor
import com.sysaxiom.mvvmbasics.data.repositorys.*
import com.sysaxiom.mvvmbasics.handlers.location.LocationHandler
import com.sysaxiom.mvvmbasics.handlers.mqtt.MqttHandler
import com.sysaxiom.mvvmbasics.handlers.network.NetworkHandler
import com.sysaxiom.mvvmbasics.ui.recyclerview.AppointmentViewModelFactory
import com.sysaxiom.mvvmbasics.ui.navigationview.relation.RelationViewModelFactory
import com.sysaxiom.mvvmbasics.ui.navigationview.terms.TermsViewModelFactory
import com.sysaxiom.mvvmbasics.ui.basicui.PrivacyViewModelFactory
import com.sysaxiom.mvvmbasics.ui.room.LoginViewModelFactory
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
        bind() from singleton { MVVMBasicsDatabase(instance()) }
        bind() from singleton { AuthRepository(instance(),instance()) }
        bind() from singleton { AppointmentRepository(instance()) }
        bind() from singleton { PrivacyRepository(instance()) }
        bind() from singleton { TermsRepository(instance()) }
        bind() from singleton { RelationRepository(instance()) }
        bind() from provider { AppointmentViewModelFactory(instance(),instance()) }
        bind() from provider { PrivacyViewModelFactory(instance()) }
        bind() from provider { TermsViewModelFactory(instance()) }
        bind() from provider { RelationViewModelFactory(instance()) }
        bind() from provider { LoginViewModelFactory(instance()) }

    }

}