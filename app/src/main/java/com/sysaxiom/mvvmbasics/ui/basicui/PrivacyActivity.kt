package com.sysaxiom.mvvmbasics.ui.basicui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProviders
import com.sysaxiom.mvvmbasics.R
import com.sysaxiom.mvvmbasics.data.listeners.LoaderListener
import com.sysaxiom.mvvmbasics.utils.hide
import com.sysaxiom.mvvmbasics.utils.initiazeProgressBar
import com.sysaxiom.mvvmbasics.utils.show
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_privacy.*

class PrivacyActivity : AppCompatActivity(), LoaderListener, KodeinAware {

    override val kodein by kodein()

    private val factory : PrivacyViewModelFactory by instance()

    lateinit var viewModel: PrivacyViewModel
    lateinit var progressBar: ProgressBar
    lateinit var rootLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)

        viewModel = ViewModelProviders.of(this, factory).get(PrivacyViewModel::class.java)

        rootLayout = findViewById(R.id.root_layout)
        progressBar = initiazeProgressBar(rootLayout)
        progressBar.hide(this)

        viewModel.getPrivacy()
        viewModel.loaderListener = this
        viewModel.privacyResponse.observe(this, Observer {
            textview_privacy.show()
            textview_privacy.setText(it.data)
        })
    }

    override fun onStarted() {
        progressBar.show(this)
    }

    override fun onSuccess() {
        textview_privacy.hide()
        progressBar.hide(this)
    }

    override fun onFailure(message: String) {
        textview_privacy.show()
        textview_privacy.text = message
        progressBar.hide(this)
    }
}
