package com.example.mytimber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_log).setOnClickListener {
            Timber.i("info")
        }

        findViewById<Button>(R.id.btn_exception).setOnClickListener {
            try {
                throw RuntimeException("Expected exception")
            } catch (e: Exception) {
                Timber.e(e)
            }
        }

        findViewById<Button>(R.id.btn_unexpected_exception).setOnClickListener {
            throw RuntimeException("Unexpected exception")
        }
    }
}