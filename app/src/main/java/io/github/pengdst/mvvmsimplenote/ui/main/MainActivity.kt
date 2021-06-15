package io.github.pengdst.mvvmsimplenote.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import io.github.pengdst.mvvmsimplenote.R
import io.github.pengdst.mvvmsimplenote.data.db.NoteDatabase
import io.github.pengdst.mvvmsimplenote.data.db.model.NoteEntity
import io.github.pengdst.mvvmsimplenote.data.network.RetrofitBuilder
import io.github.pengdst.mvvmsimplenote.data.network.service.NoteService
import io.github.pengdst.mvvmsimplenote.data.repository.NoteRepository
import io.github.pengdst.mvvmsimplenote.ui.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val noteDao = NoteDatabase.newInstance(this).noteDao()
        val apiService = RetrofitBuilder.newInstance().create(NoteService::class.java)
        val repository = NoteRepository(noteDao, apiService)
        val factory = ViewModelFactory(repository)

        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        mainViewModel.getAllNotes().observe(this) {
            Log.d("TAG", "Notes: $it")
        }

        mainViewModel.insert(NoteEntity(0, "Nama"))
    }
}