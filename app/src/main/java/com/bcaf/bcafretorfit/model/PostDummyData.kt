package com.bcaf.bcafretorfit.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class PostDummyData(
	@PrimaryKey(autoGenerate = true) val id :Int,

	@field:SerializedName("owner")
	val owner: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("likes")
	val likes: Int? = null,

	@field:SerializedName("tags")
	val tags: List<String?>? = null
)
