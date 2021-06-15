package io.github.pengdst.mvvmsimplenote.data.network.response

import com.google.gson.annotations.SerializedName

data class GetAllNotesResponse(

	@field:SerializedName("data")
	val data: List<NoteDto>? = null
)

