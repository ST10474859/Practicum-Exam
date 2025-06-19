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