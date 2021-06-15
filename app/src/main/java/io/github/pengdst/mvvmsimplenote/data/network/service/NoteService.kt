package io.github.pengdst.mvvmsimplenote.data.network.service

import io.github.pengdst.mvvmsimplenote.data.network.response.CreateNoteResponse
import io.github.pengdst.mvvmsimplenote.data.network.response.GetAllNotesResponse
import io.github.pengdst.mvvmsimplenote.data.network.response.NoteDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created on 6/15/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
interface NoteService {

    @GET("notes")
    suspend fun getAllNotes(): Response<GetAllNotesResponse>

    @FormUrlEncoded
    @POST("notes")
    suspend fun createNote(@Body note: NoteDto): Response<CreateNoteResponse>

}