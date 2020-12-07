package com.theintsuhtwe.doctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.doctor.mvp.views.MainView
import com.theintsuhtwe.shared.data.models.SpecialitiesModelImpl
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter

class MainPresenterImpl : MainPresenter, AbstractBasePresenter<MainView>(){
    var mTheCareModel = SpecialitiesModelImpl
    override fun onUiReady(id : String,lifecycleOwner: LifecycleOwner) {
        getAllData(id, lifecycleOwner)

        mTheCareModel.getDeviceToken()


    }

    override fun onTapStartConsultation() {

    }


    private fun getAllData(id: String, lifecycleOwner: LifecycleOwner){
        mTheCareModel.getGeneralQuestionsBySpecailities(
            id,
            onSuccess = {
                mView?.displayGeneralQuestionByCateogry(it)
            },
            onFaiure = {

            }
        )

        mTheCareModel.getSpecialQuestionsBySpecailities(
            id,
            onSuccess = {
                mView?.displaySpecialQuestionByCateogry(it)
            },
            onFaiure = {

            }
        )

//        mTheCareModel.getMedicinesBySpecailities(
//            id,
//            onSuccess = {
//                mView?.displayMedicineQuestionByCateogry(it)
//            },
//            onFaiure = {
//
//            }
//        )
    }

}