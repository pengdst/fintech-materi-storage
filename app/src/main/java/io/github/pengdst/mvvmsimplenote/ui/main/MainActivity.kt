package io.github.pengdst.mvvmsimplenote.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import io.github.pengdst.mvvmsimplenote.R
import io.github.pengdst.mvvmsimplenote.data.db.NoteDatabase
import io.github.pengdst.mvvmsimplenote.data.db.model.NoteEntity
import io.github.pengdst.mvvmsimplenote.data.network.RetrofitBuilder
import io.github.pengdst.mvvmsimplenote.data.network.service.NoteService
import io.github.pengdst.mvvmsimplenote.data.repository.NoteRepository
import io.github.pengdst.mvvmsimplenote.ui.ViewModelFactory
import io.github.pengdst.mvvmsimplenote.ui.firestore.FirestoreActivity
import io.github.pengdst.mvvmsimplenote.ui.messaging.MessagingActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var btnOpenMessaging: Button
    private lateinit var btnOpenFirestore: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOpenMessaging = findViewById(R.id.btn_open_messaging)
        btnOpenFirestore = findViewById(R.id.btn_open_firestore)

        val noteDao = NoteDatabase.newInstance(this).noteDao()
        val apiService = RetrofitBuilder.newInstance().create(NoteService::class.java)
        val repository = NoteRepository(noteDao, apiService)
        val factory = ViewModelFactory(repository)

        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        mainViewModel.getAllNotes().observe(this) {
            Log.d("TAG", "Notes: $it")
        }

        mainViewModel.insert(NoteEntity(0, "Nama"))

        btnOpenMessaging.setOnClickListener {
            val intent = Intent(applicationContext, MessagingActivity::class.java)
            startActivity(intent)
        }

        btnOpenFirestore.setOnClickListener {
            val intent = Intent(applicationContext, FirestoreActivity::class.java)
            startActivity(intent)
        }
    }
}