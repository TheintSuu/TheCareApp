package com.theintsuhtwe.shared.data.models

import com.theintsuhtwe.shared.data.vos.CheckOutVO
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.network.CloudFirestoreFirebaseApiImpl

object  CheckoutModelImpl : CheckoutModel, BaseModel() {

    private val mFirebase  = CloudFirestoreFirebaseApiImpl
//    override fun addCheckout(checkout: CheckOutVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
//      //  mFirebase.checkOutMedicine(checkout, onSuccess, onFailure)
//    }


}