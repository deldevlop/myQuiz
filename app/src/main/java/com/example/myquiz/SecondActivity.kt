package com.example.myquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        findViewById<View>(R.id.save_button).setOnClickListener {
            val editText1 = findViewById<EditText>(R.id.editText1)
            val editText2 = findViewById<EditText>(R.id.editText2)

            val text1 = editText1.text.toString()
            val text2 = editText2.text.toString()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("text1", text1)
            intent.putExtra("text2", text2)
            startActivity(intent)
           // finish()
        }

        findViewById<View>(R.id.remove_button).setOnClickListener {
            finish()
        }
    }
}