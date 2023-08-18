package com.example.myquiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class AddCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        findViewById<View>(R.id.save_button).setOnClickListener {
            val editText1 = findViewById<EditText>(R.id.editText1)
            val editText2 = findViewById<EditText>(R.id.editText2)

            val text1 = editText1.text.toString()
            val text2 = editText2.text.toString()
            if(!text1.isNullOrBlank() && !text2.isNullOrBlank()){
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("text1", text1)
                intent.putExtra("text2", text2)
                startActivity(intent)
                overridePendingTransition(R.anim.right_in, R.anim.left_out)
            }else{
                Toast.makeText(applicationContext, "Both fields must be entered ", Toast.LENGTH_SHORT).show()
            }
        }

        val textView1 = findViewById<EditText>(R.id.editText1)
        val textView2 = findViewById<EditText>(R.id.editText2)

        val text1 = intent.getStringExtra("questionText")
        val text2 = intent.getStringExtra("answerText")
        if (!text1.isNullOrBlank() && !text2.isNullOrBlank()) {
            textView1.setText(text1)
            textView2.setText(text2)
        }

        findViewById<View>(R.id.remove_button).setOnClickListener {
            finish()
        }
    }
}