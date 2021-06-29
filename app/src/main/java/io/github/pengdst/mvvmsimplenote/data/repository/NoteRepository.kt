package io.github.pengdst.mvvmsimplenote.data.repository

import io.github.pengdst.mvvmsimplenote.data.db.dao.NoteDao
import io.github.pengdst.mvvmsimplenote.data.db.model.NoteEntity
import io.github.pengdst.mvvmsimplenote.data.network.response.NoteDto
import io.github.pengdst.mvvmsimplenote.data.network.service.NoteService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created on 6/7/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

class NoteRepository(
    private val noteDao: NoteDao,
    private val noteService: NoteService
) : BaseRepository() {

    fun getAllNotes() = noteDao.getAllNotes()

    suspend fun getOnlineNotes(): List<NoteDto> {
        return withContext(Dispatchers.IO) {
            val response = noteService.getAllNotes()
            try {
                response.body()?.data ?: emptyList()
            } catch (e: Exception) {
                emptyList()
            }
        }
    }

    suspend fun createOnlineNote(noteDto: NoteDto): NoteDto? {
        return withContext(Dispatchers.IO) {
            val response = noteService.createNote(noteDto)
            try {
                response.body()?.data
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun insertNote(noteEntity: NoteEntity) = withContext(Dispatchers.IO) {
        noteDao.insert(noteEntity)
    }

    suspend fun updateNote(noteEntity: NoteEntity) = withContext(Dispatchers.IO) {
        noteDao.update(noteEntity)
    }

    suspend fun deleteNote(noteEntity: NoteEntity) = withContext(Dispatchers.IO) {
        noteDao.delete(noteEntity)
    }

}