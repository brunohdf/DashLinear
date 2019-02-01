package com.example.bdias.cardhome.ui.main.dashboardcard

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

abstract class BaseDashboardCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private var successContainer: SuccessContainer? = null
    private var failureContainer: FailureContainer? = null
    private var loadingContainer: LoadingContainer? = null

    fun showContent() {
        successContainer?.show()
        failureContainer?.hide()
        loadingContainer?.hide()
    }

    fun showLoading() {
        loadingContainer?.show()
        successContainer?.hide()
        failureContainer?.hide()
    }

    fun showError() {
        failureContainer?.show()
        successContainer?.hide()
        loadingContainer?.hide()

        if (failureContainer == null) {
            this.hide()
        }
    }

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        super.addView(child, params)

        when (child) {
            is SuccessContainer -> this.successContainer = child
            is FailureContainer -> this.failureContainer = child
            is LoadingContainer -> this.loadingContainer = child
        }

        child?.visibility = View.GONE
    }

    private fun View.hide() {
        this.animate().alpha(0.0f).setListener(object: AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                visibility = View.GONE
            }
        })
    }

    private fun View.show() {
        this.animate().alpha(1.0f).setListener(object: AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                visibility = View.VISIBLE
            }
        })
    }
}
