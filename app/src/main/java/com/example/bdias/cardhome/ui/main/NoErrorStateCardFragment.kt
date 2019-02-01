package com.example.bdias.cardhome.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bdias.cardhome.R
import kotlinx.android.synthetic.main.fragment_no_error_state_card.*

class NoErrorStateCardFragment : Fragment() {

    private lateinit var firstCardViewModel: NoErrorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_no_error_state_card, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        firstCardViewModel = ViewModelProviders.of(this).get(NoErrorViewModel::class.java)
        firstCardViewModel.loadFailure()

        setupObservers()
    }

    private fun setupObservers() {
        firstCardViewModel.failure.observe(this, Observer {
            thirdCard.showError()
        })

        firstCardViewModel.loading.observe(this, Observer {
            thirdCard.showLoading()
            loading.animate()
        })
    }

    class NoErrorViewModel : ViewModel() {

        val loading = MutableLiveData<Boolean>()
        val failure = MutableLiveData<Boolean>()

        fun loadFailure() {
            loading.value = true

            val handler = Handler()
            handler.postDelayed({
                failure.value = true
            }, 5000)

        }
    }
}
