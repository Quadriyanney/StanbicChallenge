package com.quadriyanney.stanbicchallenge.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.quadriyanney.stanbicchallenge.R
import com.quadriyanney.stanbicchallenge.commons.Constants
import com.quadriyanney.stanbicchallenge.commons.Utils
import com.quadriyanney.stanbicchallenge.commons.showAlertDialog
import com.quadriyanney.stanbicchallenge.fragments.CoachesFragment
import com.quadriyanney.stanbicchallenge.fragments.QuestionFragment
import com.quadriyanney.stanbicchallenge.model.Coach
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CoachesFragment.FragmentInteractionListener {

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var username: String
    private var position = 0
    private var stage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        stage = intent.getIntExtra(Constants.EXTRA_STAGE, 1)
        username = intent.getStringExtra(Constants.EXTRA_USERNAME)

        if (stage > 1) {
            showNextQuestion()
        } else {
            showCoaches()
        }

        fabNext.setOnClickListener { showNextQuestion() }

        fabDone.setOnClickListener {
            showAlertDialog("Congratulations!!!",
                    "Thanks for taking today's challenge, you now have +50 points." +
                            "\nPS: We have a different challenge waiting for you next time :)"
            ) {
                stage = if (stage == 2) 1 else 2
                sharedPreferences.edit().apply {
                    putInt(Constants.PREFERENCE_STAGE, stage)
                    putInt(Constants.PREFERENCE_POINTS, 50)
                }.apply()

                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun showNextQuestion() {
        val questions = if (stage == 2) {
            Utils.getTestQuestionsTwo()
        } else {
            Utils.getTestQuestionsOne()
        }

        if (position == 0 && stage == 1) {
            showAlertDialog("Hey $username",
                    "This is stage 1 of the challenge. " +
                            "We would like to know more about you, click on OK to proceed")
        }

        if (position == 0 && stage == 2) {
            showAlertDialog("Welcome back $username",
                    "You are about to start the second stage of the challenge which is " +
                            "aimed at knowing more about you financially and your take on some " +
                            "of our services, kindly click OK to proceed.\n\n " +
                            "Points: ${sharedPreferences.getInt(Constants.PREFERENCE_POINTS, 50)}")
        }

        if (position < questions.size) {
            showFragment(QuestionFragment.newInstance(questions[position]))
            position++

            if (position == questions.size) {
                fabDone.visibility = View.VISIBLE
            } else {
                fabNext.visibility = View.VISIBLE
            }
        }
    }

    private fun showCoaches() {
        showFragment(CoachesFragment.newInstance(username))
    }

    private fun showFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }

    override fun showQuestions(coach: Coach) {
        showNextQuestion()
    }
}
