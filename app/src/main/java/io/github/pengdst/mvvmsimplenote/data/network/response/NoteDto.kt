package io.github.pengdst.mvvmsimplenote.data.network.response

import com.google.gson.annotations.SerializedName

data class NoteDto(

	@field:SerializedName("note")
	val note: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)