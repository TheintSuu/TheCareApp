package com.theintsuhtwe.shared.adapters

import androidx.recyclerview.widget.RecyclerView
import com.theintsuhtwe.shared.viewholders.BaseViewHolder

abstract class BaseAdapter<T: BaseViewHolder<W>,W> : RecyclerView.Adapter<T>(){
    private var mDataList:MutableList<W> = mutableListOf()

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.position(position.toLong())

        holder.bindData(mDataList[position])


    }

    override fun getItemCount(): Int {
        return mDataList.size

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setData (data : List<W>){
        mDataList.clear()
        mDataList.addAll(data)
        notifyDataSetChanged()
    }

}