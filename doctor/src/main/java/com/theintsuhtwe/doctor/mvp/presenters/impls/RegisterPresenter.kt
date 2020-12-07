package com.theintsuhtwe.doctor.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.doctor.mvp.views.RegisterView
import com.theintsuhtwe.shared.data.models.AuthenticationModel
import com.theintsuhtwe.shared.data.models.AuthenticationModelImpl
import com.theintsuhtwe.shared.data.vos.CategoryVO
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter

interface RegisterPresenter : BasePresenter<RegisterView> {

    fun onUiReady(lifecycleOwner: LifecycleOwner)

    fun onTapRegister(email:String,password:String,userName:String, categoryVO: CategoryVO)

}