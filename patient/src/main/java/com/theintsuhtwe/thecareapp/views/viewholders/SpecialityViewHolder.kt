package com.theintsuhtwe.thecareapp.views.viewholders



import android.view.View
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.theintsuhtwe.shared.data.vos.CategoryVO
import com.theintsuhtwe.shared.utils.loadImage
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import kotlinx.android.synthetic.main.item_recent_doctor.view.*
import kotlinx.android.synthetic.main.item_speciality.view.*
import kotlinx.android.synthetic.main.item_speciality.view.tvSpecialityName

class SpecialityViewHolder(private val mDelegate: SpecialitiesItemDelegate, itemView: View) : BaseViewHolder<CategoryVO>(itemView) {


    init {
        itemView.setOnClickListener {
            mData?.let {
                mDelegate.onTapSpecialities(it.name)
            }

        }
    }

    override fun bindData(data: CategoryVO) {
        mData = data

        itemView.tvSpecialityName.text = data.name

        Glide.with(itemView)
                .load(data.image)
                .optionalFitCenter()
                .into(itemView.ivSpecialityImage)

        //data.image.let { itemView.ivSpecialityImage.loadImage(it.toUri())}
    }
}