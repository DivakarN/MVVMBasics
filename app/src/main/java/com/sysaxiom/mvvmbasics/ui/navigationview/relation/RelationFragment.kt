package com.sysaxiom.mvvmbasics.ui.navigationview.relation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sysaxiom.mvvmbasics.R
import com.sysaxiom.mvvmbasics.data.listeners.LoaderListener
import com.sysaxiom.mvvmbasics.utils.hide
import com.sysaxiom.mvvmbasics.utils.show
import kotlinx.android.synthetic.main.fragment_relation.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class RelationFragment : Fragment(),LoaderListener, KodeinAware {

    override val kodein by kodein()

    private val factory: RelationViewModelFactory by instance()

    private lateinit var viewModel: RelationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_relation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(RelationViewModel::class.java)

        viewModel.getRelation()
        viewModel.loaderListener = this

        val adapter= RelationAdapter(listOf())
        recylerview_relation.layoutManager = LinearLayoutManager(context)
        recylerview_relation.adapter = adapter

        viewModel.relationResponse.observe(viewLifecycleOwner, Observer {
            adapter.relatives = it.data
            adapter.notifyDataSetChanged()
        })
    }


    override fun onStarted() {
        progressBar.show(activity!!)
    }

    override fun onSuccess() {
        textView.hide()
        progressBar.hide(activity!!)
    }

    override fun onFailure(message: String) {
        textView.show()
        textView.text = message
        progressBar.hide(activity!!)
    }


}
