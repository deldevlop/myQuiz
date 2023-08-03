package com.example.myquiz

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    var isActive : Boolean = true

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.flashcardAnswer1).setOnClickListener{
            findViewById<View>(R.id.flashcardAnswer1).setBackgroundColor(resources.getColor(R.color.red, null))
            findViewById<View>(R.id.flashcardAnswer3).setBackgroundColor(resources.getColor(R.color.green, null))
        }

        findViewById<View>(R.id.flashcardAnswer3).setOnClickListener{
            findViewById<View>(R.id.flashcardAnswer1).setBackgroundColor(resources.getColor(R.color.yellow, null))
            findViewById<View>(R.id.flashcardAnswer3).setBackgroundColor(resources.getColor(R.color.green, null))
        }


        findViewById<ImageView>(R.id.toggle_choices_visibility).setOnClickListener {
            findViewById<ImageView>(R.id.toggle_choices_visibility).visibility = ImageView.INVISIBLE
            findViewById<ImageView>(R.id.toggle_choices_invisibility).visibility = ImageView.VISIBLE
            findViewById<View>(R.id.flashcardAnswer1).visibility = View.VISIBLE
            findViewById<View>(R.id.flashcardAnswer2).visibility = View.VISIBLE
            findViewById<View>(R.id.flashcardAnswer3).visibility = View.VISIBLE
        }

        findViewById<ImageView>(R.id.toggle_choices_invisibility).setOnClickListener {
            findViewById<ImageView>(R.id.toggle_choices_invisibility).visibility = ImageView.INVISIBLE
            findViewById<ImageView>(R.id.toggle_choices_visibility).visibility = ImageView.VISIBLE
            findViewById<View>(R.id.flashcardAnswer1).visibility = View.INVISIBLE
            findViewById<View>(R.id.flashcardAnswer2).visibility = View.INVISIBLE
            findViewById<View>(R.id.flashcardAnswer3).visibility = View.INVISIBLE
        }
    }
}