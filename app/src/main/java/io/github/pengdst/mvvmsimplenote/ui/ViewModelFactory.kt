package io.github.pengdst.mvvmsimplenote.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.pengdst.mvvmsimplenote.data.repository.NoteRepository
import io.github.pengdst.mvvmsimplenote.ui.main.MainViewModel
import java.lang.ClassCastException

/**
 * Created on 6/7/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class ViewModelFactory(private val noteRepository: NoteRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isInstance(MainViewModel::class.java)){
            MainViewModel(noteRepository)
        }
        else {
            throw ClassCastException("Cannot provide ${modelClass.name}")
        } as T
    }
}