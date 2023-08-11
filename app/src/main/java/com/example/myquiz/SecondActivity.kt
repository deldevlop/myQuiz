package com.example.myquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        findViewById<View>(R.id.save_button).setOnClickListener {
            val data = Intent()

            val editText1 = findViewById<View>(R.id.editText1)
            val editText2 = findViewById<View>(R.id.editText2)

            val text1 = editText1.toString()
            val text2 = editText2.toString()

            data.putExtra("text1", text1)
            data.putExtra("text2", text2)

            setResult(RESULT_OK, data) // set result code and bundle data for response

            finish()
        }

        findViewById<View>(R.id.remove_button).setOnClickListener {
            finish()
        }
    }
}