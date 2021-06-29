package io.github.pengdst.mvvmsimplenote.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.pengdst.mvvmsimplenote.data.repository.BaseRepository
import io.github.pengdst.mvvmsimplenote.data.repository.FirestoreNoteRepository
import io.github.pengdst.mvvmsimplenote.data.repository.NoteRepository
import io.github.pengdst.mvvmsimplenote.ui.firestore.FirestoreViewModel
import io.github.pengdst.mvvmsimplenote.ui.main.MainViewModel

/**
 * Created on 6/7/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class ViewModelFactory(private val repostories: BaseRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repostories as NoteRepository) as T
            }
            modelClass.isAssignableFrom(FirestoreViewModel::class.java) -> {
                FirestoreViewModel(repostories as FirestoreNoteRepository) as T
            }
            else -> throw ClassCastException("Cannot provide ${modelClass.name}")
        }
    }
}