package io.github.pengdst.mvvmsimplenote.ui.firestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.pengdst.mvvmsimplenote.data.db.model.NoteEntity
import io.github.pengdst.mvvmsimplenote.data.network.response.NoteDto
import io.github.pengdst.mvvmsimplenote.data.repository.FirestoreNoteRepository
import io.github.pengdst.mvvmsimplenote.data.repository.NoteRepository
import kotlinx.coroutines.launch

/**
 * Created on 6/7/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class FirestoreViewModel(private val repository: FirestoreNoteRepository) : ViewModel() {

    fun getAllNote(): LiveData<List<NoteDto>> {
        val liveData = MutableLiveData<List<NoteDto>>()
        viewModelScope.launch {
            liveData.postValue(repository.getAllNotes())
        }
        return liveData
    }

    fun addNote(noteDto: NoteDto) {
        viewModelScope.launch {
            repository.addNote(noteDto)
        }
    }

    fun updateNote(noteDto: NoteDto) {
        viewModelScope.launch {
            repository.updateNote(noteDto)
        }
    }

    fun delete(noteDto: NoteDto) {
        viewModelScope.launch {
            repository.deleteNote(noteDto)
        }
    }

}