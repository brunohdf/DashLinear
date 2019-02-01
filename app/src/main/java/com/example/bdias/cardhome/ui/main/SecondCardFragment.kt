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
import com.example.bdias.cardhome.ui.main.dashboardcard.HomeCardActions
import kotlinx.android.synthetic.main.fragment_second_card.*
import kotlinx.android.synthetic.main.fragment_second_card.view.*

class SecondCardFragment : Fragment() {

    private lateinit var firstCardViewModel: FirstCardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second_card, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        firstCardViewModel = ViewModelProviders.of(this).get(FirstCardViewModel::class.java)
        firstCardViewModel.loadData()

        setupObservers()

        secondCard.setOnRetry(object :
            HomeCardActions {
            override fun onRetry() {
                firstCardViewModel.loadData()
            }
        }, message, error)
    }

    private fun setupObservers() {
        firstCardViewModel.success.observe(this, Observer { text ->
            text?.let {
                secondCard.message.text = it
                secondCard.showContent()
            }
        })

        firstCardViewModel.failure.observe(this, Observer {
            secondCard.showError()
        })

        firstCardViewModel.loading.observe(this, Observer {
            secondCard.showLoading()
            loading.animate()
        })
    }

    class FirstCardViewModel : ViewModel() {

        val success = MutableLiveData<String>()
        val loading = MutableLiveData<Boolean>()
        val failure = MutableLiveData<Boolean>()

        fun loadData() {
            loading.value = true

            val handler = Handler()
            handler.postDelayed({

                if (((0..10).random()) % 2 == 0)
                    success.value = "Second Card"
                else
                    failure.value = true
            }, 2000)

        }
    }
}
