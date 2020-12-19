package com.theintsuhtwe.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.delegates.MedicineItemDelegate
import com.theintsuhtwe.doctor.views.viewholders.PrescriptionViewHolder
import com.theintsuhtwe.shared.adapters.BaseAdapter
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder


class PrescriptionAdapter (private val mDelegate: MedicineItemDelegate) : BaseAdapter<BaseViewHolder<MedicineVO>, MedicineVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MedicineVO> {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_prescription,parent,false)
        return  PrescriptionViewHolder(mDelegate,v)
    }

    private var filter : MutableList<MedicineVO> = arrayListOf()
    private var listFilter : MutableList<MedicineVO> = arrayListOf()

    fun setList(list: MutableList<MedicineVO>){
        filter.clear()
        filter.addAll(list)
    }
    fun setFilter(list: MutableList<MedicineVO>){
        listFilter.clear()
        listFilter.addAll(list)
    }

     fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val filterResults = FilterResults()
                if (constraint == null || constraint.length == 0) {
                    filterResults.count = filter.size
                    filterResults.values = filter
                } else {
                    val resultsModel: MutableList<MedicineVO> = ArrayList()
                    val searchStr = constraint.toString().toLowerCase()
                    for (itemsModel in filter) {

                            if (itemsModel.name.toLowerCase()
                                            ?.contains(searchStr)!!
                            ) {
                                resultsModel.add(itemsModel)
                            }

                        filterResults.count = resultsModel.size
                        filterResults.values = resultsModel
                    }
                }
                return filterResults
            }

            override fun publishResults(
                    constraint: CharSequence,
                    results: FilterResults
            ) {
                mDataList = results.values as ArrayList<MedicineVO>
                notifyDataSetChanged()
            }
        }
    }



}