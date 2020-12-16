package com.theintsuhtwe.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.delegates.ConsultationItemDelegate
import com.theintsuhtwe.doctor.utils.SessionManager
import com.theintsuhtwe.doctor.views.viewholders.ChatDoctorViewHolder
import com.theintsuhtwe.doctor.views.viewholders.RequestViewHolder
import com.theintsuhtwe.shared.adapters.BaseAdapter
import com.theintsuhtwe.shared.data.vos.MessageVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder

class ChatHistoryAdapter   (private val mDelegate: ConsultationItemDelegate) : BaseAdapter<BaseViewHolder<MessageVO>, MessageVO>() {
   var Patient_ViewType = 2
   var Doctor_ViewType = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MessageVO> {
        when(viewType){
            Doctor_ViewType -> {
                val v = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_doctor,parent,false)
                return  ChatDoctorViewHolder(mDelegate,v)
            }
            else ->{
                val v = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_bypatient,parent,false)
                return  ChatDoctorViewHolder(mDelegate,v)
            }
        }


    }

    override fun getItemViewType(position: Int): Int {

            when{
                mDataList[position].senderId == SessionManager.doctor_id.toString() ->{
                    return Doctor_ViewType
                }else->{
                return Patient_ViewType
            }
            }

    }

}