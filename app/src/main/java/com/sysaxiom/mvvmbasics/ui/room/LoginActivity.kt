package com.sysaxiom.mvvmbasics.ui.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sysaxiom.mvvmbasics.R
import com.sysaxiom.mvvmbasics.data.listeners.LoaderListener
import com.sysaxiom.mvvmbasics.ui.recyclerview.AppointmentViewModel
import com.sysaxiom.mvvmbasics.ui.recyclerview.AppointmentViewModelFactory
import com.sysaxiom.mvvmbasics.utils.hide
import com.sysaxiom.mvvmbasics.utils.initiazeProgressBar
import com.sysaxiom.mvvmbasics.utils.initiazeTextview
import com.sysaxiom.mvvmbasics.utils.show
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), LoaderListener, KodeinAware {

    override val kodein by kodein()

    private val factory : LoginViewModelFactory by instance()

    lateinit var viewModel: LoginViewModel
    lateinit var progressBar: ProgressBar
    lateinit var rootLayout: ConstraintLayout
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)

        rootLayout = findViewById(R.id.root_layout)
        progressBar = initiazeProgressBar(rootLayout)
        progressBar.hide(this)
        textView = initiazeTextview(rootLayout)
        textView.hide()

        viewModel.login()
        viewModel.loaderListener = this

        viewModel.getUserData().observe(this, Observer {
            if(it != null){
                textView.show()
                textView.setText(it.toString())
            }
        })

        viewModel.getRelative().observe(this, Observer {
            if(it != null){
                println(it)
            }
        })

        viewModel.getRelativeMaster().observe(this, Observer {
            if(it != null){
                println(it)
            }
        })

        viewModel.getSpeciality().observe(this, Observer {
            if(it != null){
                println(it)
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
