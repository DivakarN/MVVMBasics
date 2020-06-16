package com.sysaxiom.mvvmbasics.ui.navigationview.terms

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.sysaxiom.mvvmbasics.R
import com.sysaxiom.mvvmbasics.data.listeners.LoaderListener
import com.sysaxiom.mvvmbasics.utils.hide
import com.sysaxiom.mvvmbasics.utils.initiazeProgressBar
import com.sysaxiom.mvvmbasics.utils.show
import kotlinx.android.synthetic.main.fragment_terms.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class TermsFragment : Fragment(), LoaderListener, KodeinAware {

    override val kodein by kodein()

    private val factory: TermsViewModelFactory by instance()

    private lateinit var viewModel: TermsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_terms, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, factory).get(TermsViewModel::class.java)

        viewModel.getTerms()
        viewModel.loaderListener = this

        viewModel.termsResponse.observe(viewLifecycleOwner, Observer {
            textview_terms.show()
            textview_terms.setText(it.data)
        })
    }

    override fun onStarted() {
        progressBar.show(activity!!)
    }

    override fun onSuccess() {
        textview_terms.hide()
        progressBar.hide(activity!!)
    }

    override fun onFailure(message: String) {
        textview_terms.show()
        textview_terms.text = message
        progressBar.hide(activity!!)
    }

}
