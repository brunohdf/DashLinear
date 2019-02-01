package com.example.bdias.cardhome.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bdias.cardhome.R
import com.example.bdias.cardhome.ui.main.dashboardcard.HomeCardActions
import kotlinx.android.synthetic.main.fragment_first_card.*
import kotlinx.android.synthetic.main.fragment_first_card.view.*

class FirstCardFragment : Fragment() {

    private lateinit var firstCardViewModel: FirstCardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first_card, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        firstCardViewModel = ViewModelProviders.of(this).get(FirstCardViewModel::class.java)
        firstCardViewModel.loadData()

        setupObservers()

        firstCard.setOnRetry(object :
            HomeCardActions {
            override fun onRetry() {
                firstCardViewModel.loadData()
            }
        }, content, error)
    }

    private fun setupObservers() {
        firstCardViewModel.success.observe(this, Observer { text ->
            text?.let {
                firstCard.content.text = it
                firstCard.showContent()
            }
        })

        firstCardViewModel.failure.observe(this, Observer {
            firstCard.showError()
        })

        firstCardViewModel.loading.observe(this, Observer {
            firstCard.showLoading()
            loading.animate()
        })
    }

}
