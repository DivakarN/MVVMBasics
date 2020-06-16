package com.sysaxiom.mvvmbasics.ui.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sysaxiom.mvvmbasics.R
import com.sysaxiom.mvvmbasics.data.listeners.LoaderListener
import com.sysaxiom.mvvmbasics.utils.*
import kotlinx.android.synthetic.main.activity_appointment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class AppointmentActivity : AppCompatActivity(),
    LoaderListener, KodeinAware {

    override val kodein by kodein()

    private val factory : AppointmentViewModelFactory by instance()

    lateinit var viewModel: AppointmentViewModel
    lateinit var progressBar: ProgressBar
    lateinit var rootLayout: ConstraintLayout
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment)

        viewModel = ViewModelProviders.of(this, factory).get(AppointmentViewModel::class.java)

        rootLayout = findViewById(R.id.root_layout)
        progressBar = initiazeProgressBar(rootLayout)
        progressBar.hide(this)
        textView = initiazeTextview(rootLayout)
        textView.hide()

        viewModel.getAppointment()
        viewModel.loaderListener = this

        val adapter= AppointmentAdapter(listOf())
        recylerview_appointment.layoutManager = LinearLayoutManager(this)
        recylerview_appointment.adapter = adapter

        viewModel.appointmentResponse.observe(this, Observer {
            adapter.appointmentList = it.data
            adapter.notifyDataSetChanged()
        })

        viewModel.getNetwork().observe(this, Observer {
            if(it.isNetworkAvailable.equals(true)){
                println("Internet Availble")
            } else if(it.isNetworkAvailable.equals(false)){
                println("Internet Not Availble")
            }
        })

        viewModel.getLocation().observe(this, Observer {
            if(it.isPermissionAvailable.equals(true)){
                println("Location Data$it")
            } else if(it.isPermissionAvailable.equals(false)){
                println("Location Data$it")
            }
        })

        viewModel.getMqtt().observe(this, Observer {
            if(it.isConnected.equals(true)){
                println("Connected")
            } else if(it.isConnected.equals(false)){
                println("Not Connected")
            }
        })
    }

    override fun onStarted() {
        progressBar.show(this)
    }

    override fun onSuccess() {
        textView.hide()
        progressBar.hide(this)
    }

    override fun onFailure(message: String) {
        textView.show()
        textView.text = message
        progressBar.hide(this)
    }

}
