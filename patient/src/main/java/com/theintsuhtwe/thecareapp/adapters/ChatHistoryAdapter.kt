package com.theintsuhtwe.thecareapp.adapters

import android.se.omapi.Session
import android.view.LayoutInflater
import android.view.ViewGroup
import com.theintsuhtwe.shared.adapters.BaseAdapter
import com.theintsuhtwe.shared.data.vos.MessageVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import com.theintsuhtwe.thecareapp.utils.SessionManager
import com.theintsuhtwe.thecareapp.views.viewholders.ChatDoctorViewHolder
import com.theintsuhtwe.thecareapp.views.viewholders.ChatPatientViewHolder

class ChatHistoryAdapter   (private val mDelegate: SpecialitiesItemDelegate) : BaseAdapter<BaseViewHolder<MessageVO>, MessageVO>() {
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
                return  ChatPatientViewHolder(mDelegate,v)
            }
        }


    }

    override fun getItemViewType(position: Int): Int {

            when{
                mDataList[position].senderId ==  SessionManager.patient_id.toString() ->{
                    return Patient_ViewType
                }else->{
                return Doctor_ViewType
            }
            }

    }

}