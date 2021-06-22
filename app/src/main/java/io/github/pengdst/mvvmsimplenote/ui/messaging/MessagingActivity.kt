package io.github.pengdst.mvvmsimplenote.ui.messaging

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import io.github.pengdst.mvvmsimplenote.R

class MessagingActivity : AppCompatActivity() {

    private lateinit var btnSubscribe: Button
    private lateinit var etTopic: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messaging)

        btnSubscribe = findViewById(R.id.btn_subscribe)
        etTopic = findViewById(R.id.et_topic)

        btnSubscribe.setOnClickListener {
            val topic = etTopic.text.toString()
            Firebase.messaging.subscribeToTopic(topic)
                .addOnCompleteListener { task ->
                    val msg = if (task.isSuccessful) "Subscribed to $topic topic" else "Subscribe Failed"
                    Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                }
        }
    }
}