package com.theintsuhtwe.shared.mvp.presenters

import com.theintsuhtwe.shared.mvp.views.BaseView

interface BasePresenter<T : BaseView> {

    fun initPresenter(view: T)

}