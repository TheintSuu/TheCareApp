package com.theintsuhtwe.shared.fragments

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import com.theintsuhtwe.shared.mvp.views.BaseView

abstract class BaseDialogFragment: DialogFragment(), BaseView {

    inline fun <reified T : AbstractBasePresenter<W>, reified W: BaseView> getPresenter(): T {
        val presenter = ViewModelProviders.of(this).get(T::class.java)
        if (this is W) presenter.initPresenter(this)
        return presenter

    }

    override fun showErrorMessage(error: String) {
        activity?.window?.decorView?.let { Snackbar.make(it, error, Snackbar.LENGTH_LONG).show() }
    }
}