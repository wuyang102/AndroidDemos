package com.daniel.android.demo.dagger2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.daniel.android.demo.ViewModelFactory
import com.daniel.android.demo.databinding.Dagger2FragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Dagger2Fragment : Fragment() {

    companion object {
        fun newInstance() = Dagger2Fragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<Dagger2ViewModel>

    private lateinit var viewModel: Dagger2ViewModel

    private lateinit var binding: Dagger2FragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(Dagger2ViewModel::class.java)
        viewModel.tsLD.observe(this, {
            binding.message.text = it!!
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Dagger2FragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}
