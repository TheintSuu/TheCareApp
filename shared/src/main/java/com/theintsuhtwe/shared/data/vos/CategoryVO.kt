package com.theintsuhtwe.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

@Entity(tableName = "category")
@IgnoreExtraProperties
data class CategoryVO(
        @PrimaryKey
         @SerializedName("id") var id: String = "",
        @SerializedName("name") var name: String = "",
        @SerializedName("image") var  image: String = ""
)


