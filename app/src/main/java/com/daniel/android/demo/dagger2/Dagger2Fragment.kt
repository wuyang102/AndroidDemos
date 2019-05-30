package com.daniel.android.demo.dagger2

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daniel.android.demo.Injectable
import com.daniel.android.demo.R
import com.daniel.android.demo.ViewModelFactory
import kotlinx.android.synthetic.main.dagger2_fragment.*
import javax.inject.Inject

class Dagger2Fragment : Fragment(), Injectable {

    companion object {
        fun newInstance() = Dagger2Fragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<Dagger2ViewModel>

    private lateinit var viewModel: Dagger2ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.dagger2_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(Dagger2ViewModel::class.java)
        viewModel.tsLD.observe(this, Observer<String> {
            message.text = it!!
        })
    }

}
