package com.example.bdias.cardhome.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Handler

class FirstCardViewModel : ViewModel() {

    val success = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val failure = MutableLiveData<Boolean>()

    fun loadData() {
        loading.value = true

        val handler = Handler()
        handler.postDelayed({

            if (((0..10).random()) % 2 == 0)
                success.value = "First Card"
            else
                failure.value = true
        }, 1000)
    }
}
