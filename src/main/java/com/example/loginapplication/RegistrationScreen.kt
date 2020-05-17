package com.example.loginapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import kotlinx.android.synthetic.main.activity_registration_screen.*

class RegistrationScreen : AppCompatActivity() {

    lateinit var handler : DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_screen)

        handler = DatabaseHelper(this)

        registerbutton.setOnClickListener {
            handler.insertUser(name.text.toString() , newUsername.text.toString() , newPassword.text.toString())
            val backhome = Intent(this,MainActivity::class.java)
            startActivity(backhome)
        }
    }
}
