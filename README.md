# Practicum-Exam
IMAD5112

// Simphiwe munalilo
// ST10474859
package com.example.practicumexam

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    // Arrays to store song details
    private val songTitles = ArrayList<String>()
    private val artistsNames = ArrayList<String>()
    private val ratings = ArrayList<Int>()
    private val comments = ArrayList<String>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton = findViewById<Button>(R.id.Playlist)
        val viewButton = findViewById<Button>(R.id.NxtBtn)
        val exitButton = findViewById<Button>(R.id.ExtBtn)

        addButton.setOnClickListener {
            addSong()
        }

        viewButton.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putStringArrayListExtra("titles", songTitles)
            intent.putStringArrayListExtra("artists", artistsNames)
            intent.putIntegerArrayListExtra("ratings", ArrayList(ratings))
            intent.putStringArrayListExtra("comments", comments)
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            finish()
        }
    }

    private fun addSong() {
        // Replace this with dialog or form input (can be improved later)
        val song = "New Song"
        val artist = "New Artist"
        val rating = 3
        val comment = "Nice song"

        if (rating < 1 || rating > 5) {
            Toast.makeText(this, "Rating must be between 1 and 5", Toast.LENGTH_SHORT).show()
            return
        }

        if (songTitles.size >= 4) {
            Toast.makeText(this, "Maximum of 4 songs allowed", Toast.LENGTH_SHORT).show()
            return
        }

        songTitles.add(song)
        artistsNames.add(artist)
        ratings.add(rating)
        comments.add(comment)

        Toast.makeText(this, "Song added to playlist", Toast.LENGTH_SHORT).show()
    }
}
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
  
