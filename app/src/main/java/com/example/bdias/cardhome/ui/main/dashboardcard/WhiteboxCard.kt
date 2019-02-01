package com.example.bdias.cardhome.ui.main.dashboardcard

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.example.bdias.cardhome.R

class WhiteboxCard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseDashboardCard(context, attrs, defStyleAttr) {

    init {
        setupStyle()
    }

    fun setOnRetry(listener: HomeCardActions, vararg views: View) {
        for (v in views) {
            v.isClickable = true
            v.isFocusableInTouchMode = true
            v.setOnClickListener { listener.onRetry() }
        }
    }

    private fun setupStyle() {
        this.background = ContextCompat.getDrawable(context, R.drawable.border_bottom)
        this.cardElevation = 0.0f

        this.setContentPadding(20, 16, 20, 0)
    }
}

interface HomeCardActions {
    fun onRetry()
}