package com.example.myquiz

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import java.security.AccessController.getContext
import kotlin.math.hypot


class MainActivity : AppCompatActivity() {

    lateinit var flashcardDatabase: FlashcardDatabase
    var allFlashcards = mutableListOf<Flashcard>()
    var currentCardDisplayedIndex = 0
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lateinit var fadeInAnimation: Animation


        flashcardDatabase = FlashcardDatabase(this)
        allFlashcards = flashcardDatabase.getAllCards().toMutableList()

        fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        if (allFlashcards.size > 0) {
            findViewById<TextView>(R.id.flashcardQuestion).text = allFlashcards[0].question
            findViewById<TextView>(R.id.flashcardAnswer).text = allFlashcards[0].answer
        }

        findViewById<View>(R.id.flashcardQuestion).setOnClickListener {
            findViewById<View>(R.id.flashcardQuestion).visibility = View.INVISIBLE
            findViewById<View>(R.id.flashcardAnswer).visibility = View.VISIBLE
        }

        findViewById<View>(R.id.flashcardAnswer).setOnClickListener {


            val answerSideView = findViewById<View>(R.id.flashcardAnswer)


            val cx = answerSideView.width / 2
            val cy = answerSideView.height / 2


            val finalRadius = hypot(cx.toDouble(), cy.toDouble()).toFloat()

            val anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius)

            findViewById<View>(R.id.flashcardQuestion).visibility = View.VISIBLE
            findViewById<View>(R.id.flashcardAnswer).visibility = View.INVISIBLE

            anim.duration = 3000
            anim.start()
        }



        findViewById<ImageView>(R.id.add_button).setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.right_in, R.anim.left_out)
        }


        findViewById<ImageView>(R.id.edit_button).setOnClickListener {
            val questionText = findViewById<TextView>(R.id.flashcardQuestion)
            val answerText = findViewById<TextView>(R.id.flashcardAnswer)

            val text1 = questionText.text.toString()
            val text2 = answerText.text.toString()
            val intent = Intent(this, AddCardActivity::class.java)
            intent.putExtra("questionText", text1)
            intent.putExtra("answerText", text2)
            startActivity(intent)
            overridePendingTransition(R.anim.right_in, R.anim.left_out)
        }

        findViewById<ImageView>(R.id.next_button).setOnClickListener {
            if (allFlashcards.size == 0) {
                return@setOnClickListener
            }

            findViewById<View>(R.id.flashcardQuestion).startAnimation(fadeInAnimation)

            currentCardDisplayedIndex++

            if(currentCardDisplayedIndex >= allFlashcards.size) {
                Snackbar.make(findViewById<TextView>(R.id.flashcardQuestion), "You've reached the end of the cards, going back to start.", Snackbar.LENGTH_SHORT).show()
                currentCardDisplayedIndex = 0
            }

            allFlashcards = flashcardDatabase.getAllCards().toMutableList()
            val (question, answer) = allFlashcards[currentCardDisplayedIndex]

            findViewById<TextView>(R.id.flashcardAnswer).text = answer
            findViewById<TextView>(R.id.flashcardQuestion).text = question

        }

        val textView1 = findViewById<TextView>(R.id.flashcardQuestion)
        val textView2 = findViewById<TextView>(R.id.flashcardAnswer)

        val text1 = intent.getStringExtra("text1")
        val text2 = intent.getStringExtra("text2")
        if (!text1.isNullOrBlank() && !text2.isNullOrBlank()) {
            textView1.text = text1
            textView2.text = text2
            flashcardDatabase.insertCard(Flashcard(text1, text2))
            allFlashcards = flashcardDatabase.getAllCards().toMutableList()
            Snackbar.make(findViewById(android.R.id.content), "Card Modified Successfully", Snackbar.LENGTH_SHORT).show()
        }


        findViewById<ImageView>(R.id.delete_button).setOnClickListener {
            val flashcardQuestionToDelete = findViewById<TextView>(R.id.flashcardQuestion).text.toString()
            flashcardDatabase.deleteCard(flashcardQuestionToDelete)
            allFlashcards = flashcardDatabase.getAllCards().toMutableList()
        }







        /*findViewById<View>(R.id.flashcardAns).setOnClickListener {
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