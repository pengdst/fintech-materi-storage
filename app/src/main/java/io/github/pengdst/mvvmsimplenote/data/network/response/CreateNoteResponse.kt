package io.github.pengdst.mvvmsimplenote.data.network.response

import com.google.gson.annotations.SerializedName

data class CreateNoteResponse(

	@field:SerializedName("data")
	val data: NoteDto? = null
)
