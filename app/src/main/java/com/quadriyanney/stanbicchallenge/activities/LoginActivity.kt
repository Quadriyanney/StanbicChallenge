package com.quadriyanney.stanbicchallenge.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.quadriyanney.stanbicchallenge.R
import com.quadriyanney.stanbicchallenge.commons.Constants
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)

        var stage = sharedPreferences.getInt(Constants.PREFERENCE_STAGE, 0)

        if (stage == 0) {
            stage++
            sharedPreferences.edit().putInt(Constants.PREFERENCE_STAGE, stage).apply()
        }

        btnLogin.setOnClickListener {
            if (etUsername.text.toString().isEmpty()) {
                etUsername.error = getString(R.string.error_blank_text_field)
                return@setOnClickListener
            }

            if (etPassword.text.toString().isEmpty()) {
                etPassword.error = getString(R.string.error_blank_text_field)
                return@setOnClickListener
            }

            startActivity(Intent(this, MainActivity::class.java).apply {
                putExtra(Constants.EXTRA_STAGE, stage)
                putExtra(Constants.EXTRA_USERNAME, etUsername.text.toString())
            })

            finish()
        }
    }
}