package com.wily.imageapp.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ImageResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("success")
	val success: Boolean
)

data class Data(

	@field:SerializedName("memes")
	val memes: List<MemesItem>
)

data class MemesItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("width")
	val width: Int,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("height")
	val height: Int,

	@field:SerializedName("box_count")
	val boxCount: Int
)
