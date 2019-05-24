package com.daniel.android.demo.dagger2

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daniel.android.demo.R

class Dagger2Fragment : Fragment() {

    companion object {
        fun newInstance() = Dagger2Fragment()
    }

    private lateinit var viewModel: Dagger2ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.dagger2_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(Dagger2ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
