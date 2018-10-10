package com.quadriyanney.stanbicchallenge

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)

        var stage = sharedPreferences.getInt("stage", 0)

        if (stage == 0) {
            stage++
            sharedPreferences.edit().putInt("stage", 1).apply()
        }

        btnServerLogin.setOnClickListener {
            if (etEmail.text.toString().isEmpty()) {
                Toast.makeText(this, "Username cannot be blank", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (etPassword.text.toString().isEmpty()) {
                Toast.makeText(this, "Enter a password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            startActivity(Intent(this, MainActivity::class.java).apply {
                putExtra("stage", stage)
                putExtra("username", etEmail.text.toString())
            })
            finish()
        }
    }
}