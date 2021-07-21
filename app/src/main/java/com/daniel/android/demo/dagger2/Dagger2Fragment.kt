package com.daniel.android.demo.dagger2

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daniel.android.demo.Injectable
import com.daniel.android.demo.ViewModelFactory
import com.daniel.android.demo.databinding.Dagger2FragmentBinding
import javax.inject.Inject

class Dagger2Fragment : Fragment(), Injectable {

    companion object {
        fun newInstance() = Dagger2Fragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<Dagger2ViewModel>

    private lateinit var viewModel: Dagger2ViewModel

    private lateinit var binding: Dagger2FragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Dagger2FragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(Dagger2ViewModel::class.java)
        viewModel.tsLD.observe(this, {
            binding.message.text = it!!
        })
    }

}
