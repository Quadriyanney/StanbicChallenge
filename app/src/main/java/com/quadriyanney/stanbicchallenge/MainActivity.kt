package com.quadriyanney.stanbicchallenge

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CoachesFragment.FragmentInteractionListener {

    private var questions1 = ArrayList<Question>()
    private var questions2 = ArrayList<Question>()
    private lateinit var username: String
    private var position = 0
    private var stage = 1
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        stage = intent.getIntExtra("stage", 1)
        username = intent.getStringExtra("username")

        questions1.add(Question("How are you feeling today?",
                arrayListOf("Great", "Good", "Not too good ", "Sad")))
        questions1.add(Question("How old are you?",
                arrayListOf("18 - 25", "26 - 40", "41 - 50", "Above 50")))
        questions1.add(Question("Gender?",
                arrayListOf("Male", "Female", "Transgender", "Other")))
        questions1.add(Question("Occupation?",
                arrayListOf("Employed", "Student", "Not Employed", "Freelancer")))
        questions1.add(Question("Marital Status?",
                arrayListOf("Married", "Single", "Divorced", "Complicated")))
        questions2.add(Question("Select your income level",
                arrayListOf("15,000 - 50,000", "50,000 - 150,000", "150,000 - 350,000", "Above 350,000")))
        questions2.add(Question("Daily Expenses?",
                arrayListOf("1,000 - 10,000", "10,000 - 30,000", "30,000 - 50,000", "Above 50,000")))
        questions2.add(Question("Select a savings plan you can afford",
                arrayListOf("1,000 - 5,000 Monthly", "5,000 - 20,000 Monthly", "20,000 - 35,000 Monthly", "Above 35,000 Monthly")))
        questions2.add(Question("What is the most important thing you would like to have soon?",
                arrayListOf("House", "Car", "Stocks", "Nothing")))
        questions2.add(Question("Rate our customer service",
                arrayListOf("Good", "Okay", "Fair", "Bad")))
        questions2.add(Question("How helpful is our Internet Banking Service",
                arrayListOf("Very Helpful", "Somewhat Helpful", "Not Helpful", "I don't have an idea")))

        if (stage > 1) {
            showNextQuestion()
        } else {
            showCoaches()
        }

        fabNext.setOnClickListener {
            showNextQuestion()
        }

        fabDone.setOnClickListener {
            AlertDialog.Builder(this)
                    .setMessage("Thanks for taking today's challenge, you now have 50 points." +
                            "\nPS: We have a different challenge waiting for you tomorrow :)")
                    .setPositiveButton("OK") { _, _ ->
                        stage = if (stage == 2) 1 else 2
                        sharedPreferences.edit().putInt("stage", stage).apply()
                        sharedPreferences.edit().putInt("points", 50).apply()
                        finish()
                    }.create()
                    .show()
        }
    }

    private fun showNextQuestion() {
        val questions = if (stage == 2) questions2 else questions1

        if (position == 0 && stage == 1) {
            AlertDialog.Builder(this)
                    .setMessage("Hey $username, this is stage 1 of the challenge. " +
                            "We would like to know more about you, click on OK to proceed")
                    .setPositiveButton("OK") { _, _ -> }
                    .create()
                    .show()
        }

        if (position == 0 && stage == 2) {
            AlertDialog.Builder(this)
                    .setMessage("Welcome back $username, you are about to start stage 2 of the challenge " +
                            "which is aimed at knowing more about you financially and your take on some " +
                            "of our services, kindly click OK to proceed." +
                            "\n\n Points: ${sharedPreferences.getInt("points", 50)}")
                    .setPositiveButton("OK") { _, _ -> }
                    .create()
                    .show()
        }

        if (position < questions.size) {
            showFragment(QuestionFragment.newInstance(questions[position]))
            position++
            if (position == questions.size){
                fabDone.visibility = View.VISIBLE
            } else {
                fabNext.visibility = View.VISIBLE
            }
        }
    }

    private fun showCoaches() {
        val fragment = CoachesFragment.newInstance(username)
        fragment.setListener(this)
        showFragment(fragment)
    }

    private fun showFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_question, fragment)
        fragmentTransaction.commit()
    }

    override fun showQuestions(coach: Coach) {
        showNextQuestion()
    }
}
