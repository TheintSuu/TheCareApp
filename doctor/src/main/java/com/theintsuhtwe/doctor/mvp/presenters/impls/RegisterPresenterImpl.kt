package com.theintsuhtwe.doctor.mvp.presenters.impls

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.doctor.mvp.views.RegisterView
import com.theintsuhtwe.shared.data.models.AuthenticationModel
import com.theintsuhtwe.shared.data.models.AuthenticationModelImpl
import com.theintsuhtwe.shared.data.models.DoctorModel
import com.theintsuhtwe.shared.data.models.DoctorModelImpl
import com.theintsuhtwe.shared.data.vos.CategoryVO
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter

class RegisterPresenterImpl : RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    private val mDoctorModel : DoctorModel = DoctorModelImpl


    override fun onUiReady(lifecycleOwner: LifecycleOwner) {

    }

    override fun onTapRegister(
        email: String,
        password: String,
        userName: String,
        categoryVO: CategoryVO
    ) {
        mAuthenticationModel.registerDoctor(email,password,userName, categoryVO, onSuccess = {
            Log.d("Register Doctor", "Success")
            mDoctorModel.addDoctor(DoctorVO(id = "",
                    cateogryId = categoryVO.id,
                    name = userName, specialities = categoryVO
            ),
                    onSuccess = {
                        Log.d("Add Doctor", "Success")
                    },
                    onFailure = {
                        Log.d("Add Doctor Failure", "Failure")
                    }
            )

        },
            onFailure = {
                Log.d("Register Doctor Failure", "Failure")
            })


    }


}