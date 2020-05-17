package com.example.loginapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var handlr : DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handlr = DatabaseHelper(this)

        register.setOnClickListener {
            val intent = Intent(this, RegistrationScreen::class.java)
            startActivity(intent)
        }
        login.setOnClickListener {
            if(handlr.userPresent(username.text.toString() , password.text.toString())){
                val loggedin = Intent(this,LoggedInScreen::class.java)
                startActivity(loggedin)
            }
            else{
                Toast.makeText(this, "Fuck you xD!" , Toast.LENGTH_SHORT).show()
            }
        }
    }
}
