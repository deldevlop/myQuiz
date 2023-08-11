package com.example.myquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    var isActive : Boolean = true

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
           result->
            val data: Intent? = result.data

            if (data != null) { // Check that we have data returned
                val string1 = data.getStringExtra("text1") // 'string1' needs to match the key we used when we put the string in the Intent
                val string2 = data.getStringExtra("text2")

                // Log the value of the strings for easier debugging
                Log.i("MainActivity", "string1: $string1")
                Log.i("MainActivity", "string2: $string2")
            } else {
                Log.i("MainActivity", "Returned null data from AddCardActivity")
            }
        }

        findViewById<View>(R.id.add_button).setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            resultLauncher.launch(intent)
        }

        findViewById<View>(R.id.flashcardQuestion).setOnClickListener {
            findViewById<View>(R.id.flashcardQuestion).visibility=View.INVISIBLE
            findViewById<View>(R.id.flashcardAnswer).visibility=View.VISIBLE
        }


        findViewById<View>(R.id.flashcardAnswer).setOnClickListener {
            findViewById<View>(R.id.flashcardQuestion).visibility=View.VISIBLE
            findViewById<View>(R.id.flashcardAnswer).visibility=View.INVISIBLE
        }
       /* part 1
       findViewById<View>(R.id.flashcardAns).setOnClickListener {
            findViewById<View>(R.id.flashcardAns).visibility = View.INVISIBLE
            findViewById<View>(R.id.flashcardQuestion).visibility = View.VISIBLE
        }

        findViewById<View>(R.id.flashcardQuestion).setOnClickListener {
            findViewById<View>(R.id.flashcardQuestion).visibility = View.INVISIBLE
            findViewById<View>(R.id.flashcardAns).visibility = View.VISIBLE
        }

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
        }*/
    }
}