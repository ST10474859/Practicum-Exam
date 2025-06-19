package com.example.practicumexam

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

        private lateinit var songList: TextView
        private lateinit var averageRating: TextView
        private lateinit var returnButton: Button
        private lateinit var showList: Button
        private lateinit var averageButton: Button

        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main2)

            // Get data from MainActivity
            val titles = intent.getStringArrayListExtra("titles") ?: arrayListOf()
            val artists = intent.getStringArrayListExtra("artists") ?: arrayListOf()
            val ratings = intent.getIntegerArrayListExtra("ratings") ?: arrayListOf()
            val comments = intent.getStringArrayListExtra("comments") ?: arrayListOf()

            // Link layout items
            songList = findViewById(R.id.songList)
            averageRating = findViewById(R.id.averageRating)
            showList = findViewById(R.id.showList)
            averageButton = findViewById(R.id.AverageButton)
            returnButton = findViewById(R.id.returnButton)

            // Show song list using a loop
            showList.setOnClickListener {
                var displayText = ""
                for (i in titles.indices) {
                    displayText += "${titles[i]} - ${artists[i]} (Rating: ${ratings[i]})\n"
                    displayText += "Comment: ${comments[i]}\n\n"
                }
                songList.text = displayText
            }

            // Calculate and display average rating
            averageButton.setOnClickListener {
                if (ratings.isNotEmpty()) {
                    val sum = ratings.sum()
                    val average = sum.toDouble() / ratings.size
                    averageRating.text = "Average Rating: %.2f".format(average)
                } else {
                    averageRating.text = "No ratings available."
                }
            }

            // Return to main screen
            returnButton.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }