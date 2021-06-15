package io.github.pengdst.mvvmsimplenote.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.pengdst.mvvmsimplenote.data.db.model.NoteEntity
import io.github.pengdst.mvvmsimplenote.data.network.response.NoteDto
import io.github.pengdst.mvvmsimplenote.data.repository.NoteRepository
import kotlinx.coroutines.launch

/**
 * Created on 6/7/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class MainViewModel(private val repository: NoteRepository) : ViewModel() {

    fun getAllNotes() = repository.getAllNotes()

    fun getOnlineNote(): LiveData<List<NoteDto>> {
        val liveData = MutableLiveData<List<NoteDto>>()
        viewModelScope.launch {
            liveData.postValue(repository.getOnlineNotes())
        }
        return liveData
    }

    fun postOnlineNote(noteDto: NoteDto) : LiveData<NoteDto> {
        val liveData = MutableLiveData<NoteDto>()
        viewModelScope.launch {
            liveData.postValue(repository.createOnlineNote(noteDto))
        }
        return liveData
    }

    fun delete(entity: NoteEntity) {
        viewModelScope.launch {
            repository.deleteNote(entity)
        }
    }

    fun insert(entity: NoteEntity) {
        viewModelScope.launch {
            repository.insertNote(entity)
        }
    }

    fun update(entity: NoteEntity) {
        viewModelScope.launch {
            repository.updateNote(entity)
        }
    }

}