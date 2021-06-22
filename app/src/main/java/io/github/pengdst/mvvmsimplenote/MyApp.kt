package io.github.pengdst.mvvmsimplenote

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

/**
 * Created on 6/22/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Firebase.messaging.subscribeToTopic("weather")
            .addOnCompleteListener { task ->
                val msg = if (task.isSuccessful) "Subscribed to weather topic" else "Subscribe Failed"
                Log.d("TAG", msg)
                Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
            }
    }
}