package com.mehedi.k_movie_db.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.k_movie_db.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
