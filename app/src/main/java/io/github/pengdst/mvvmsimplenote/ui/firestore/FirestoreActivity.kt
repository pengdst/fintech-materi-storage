package io.github.pengdst.mvvmsimplenote.ui.firestore

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import io.github.pengdst.mvvmsimplenote.R
import io.github.pengdst.mvvmsimplenote.data.network.response.NoteDto
import io.github.pengdst.mvvmsimplenote.data.repository.FirestoreNoteRepository
import io.github.pengdst.mvvmsimplenote.ui.ViewModelFactory

class FirestoreActivity : AppCompatActivity() {

    private lateinit var firestoreViewModel: FirestoreViewModel
    private lateinit var etNote: EditText
    private lateinit var etName: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firestore)

        etNote = findViewById(R.id.et_note)
        etName = findViewById(R.id.et_name)
        btnSave = findViewById(R.id.btn_save)

        val factory = ViewModelFactory(FirestoreNoteRepository())
        firestoreViewModel = ViewModelProvider(this, factory)[FirestoreViewModel::class.java]

        firestoreViewModel.getAllNote().observe(this) {
            Log.e("FirestoreActivity", "onCreate: $it")
        }

        btnSave.setOnClickListener {
            val note = etNote.text.toString().trim()
            val name = etName.text.toString().trim()

            firestoreViewModel.addNote(NoteDto(note = note, name = name))
        }
    }
}