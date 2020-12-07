package com.theintsuhtwe.shared.mvp.presenters

import androidx.lifecycle.ViewModel
import com.theintsuhtwe.shared.mvp.views.BaseView

abstract class AbstractBasePresenter<T : BaseView> : BasePresenter<T>, ViewModel() {
    var mView: T? = null

    override fun initPresenter(view: T) {
        mView = view
    }
}