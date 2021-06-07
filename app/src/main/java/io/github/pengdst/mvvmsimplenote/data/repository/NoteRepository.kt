package io.github.pengdst.mvvmsimplenote.data.repository

import io.github.pengdst.mvvmsimplenote.data.db.NoteDatabase
import io.github.pengdst.mvvmsimplenote.data.db.model.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created on 6/7/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

class NoteRepository(private val db: NoteDatabase) {

    private val noteDao = db.noteDao()

    fun getAllNotes() = noteDao.getAllNotes()

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