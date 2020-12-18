package com.theintsuhtwe.thecareapp.views.viewpod

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_pod_empty.view.*


class EmptyViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var mDelegate : Delegate ?= null

    override fun onFinishInflate() {
        super.onFinishInflate()

        setUpListener()

    }

    fun setDeledate(delegate : Delegate){
        mDelegate = delegate
    }

    fun setEmptyData(emptyMessage : String, emptyImageUrl: String){
        tvEmpty.text = emptyMessage

        Glide.with(context)
            .load(emptyImageUrl)
            .into(ivEmptyImage)


    }

    interface Delegate{
        fun onTapTryAgain()
    }

    private fun setUpListener(){

    }


}