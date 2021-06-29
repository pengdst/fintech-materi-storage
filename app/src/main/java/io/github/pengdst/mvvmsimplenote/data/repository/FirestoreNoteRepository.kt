package io.github.pengdst.mvvmsimplenote.data.repository

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.github.pengdst.mvvmsimplenote.data.network.response.NoteDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

/**
 * Created on 6/7/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

class FirestoreNoteRepository : BaseRepository() {

    suspend fun getAllNotes() = withContext(Dispatchers.IO) {
        try {
            Firebase.firestore.collection("notes")
                .get()
                .await()
                .toObjects(NoteDto::class.java)
                .toList()
        } catch (e: Exception) {
            Log.e(TAG, "getAllNotes: ", e)
            emptyList()
        }
    }

    suspend fun addNote(noteDto: NoteDto) = withContext(Dispatchers.IO) {
        val uid = Firebase.firestore.collection("notes")
            .add(noteDto)
            .await()
            .id

        Firebase.firestore.collection("notes")
            .document(uid)
            .update("uid", uid)
            .await()
    }

    suspend fun updateNote(noteDto: NoteDto) = withContext(Dispatchers.IO) {
        Firebase.firestore.collection("notes")
            .document(noteDto.uid.toString())
            .set(noteDto)
            .await()
    }

    suspend fun deleteNote(noteDto: NoteDto) = withContext(Dispatchers.IO) {
        Firebase.firestore.collection("notes")
            .document(noteDto.uid.toString())
            .delete()
            .await()
    }

    companion object {
        private const val TAG = "FirestoreNoteRepository"
    }

}