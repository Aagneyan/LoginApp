package com.example.loginapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_logged_in_screen.*
import java.lang.Exception
import java.util.*

class LoggedInScreen : AppCompatActivity() {

    private var REQUEST_CODE_SPEECH_INPUT = 100
    lateinit var handlr : DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in_screen)
        handlr = DatabaseHelper(this)
        speechText.text = handlr.exctactWord()
        micButton.setOnClickListener {
            speak()
        }
    }

    private fun speak() {
        val micInt = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        micInt.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        micInt.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        micInt.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi! speak something")

        try{
            startActivityForResult(micInt,REQUEST_CODE_SPEECH_INPUT)
        }catch (e: Exception){
            Toast.makeText(this, e.message , Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        handlr = DatabaseHelper(this)
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_CODE_SPEECH_INPUT -> {
                if(resultCode == Activity.RESULT_OK && null!=data){
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    if(result[0].equals(handlr.exctactWord())){
                        Toast.makeText(this, "U got it right" , Toast.LENGTH_SHORT).show()
                    }
                    textView2.text = result[0]
                }
            }
        }
    }
}
