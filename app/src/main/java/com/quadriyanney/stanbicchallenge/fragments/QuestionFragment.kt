package com.quadriyanney.stanbicchallenge.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.quadriyanney.stanbicchallenge.R
import com.quadriyanney.stanbicchallenge.model.Question
import kotlinx.android.synthetic.main.card_layout.*

class QuestionFragment : Fragment() {

    private lateinit var question: Question

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        question = arguments?.getParcelable(ARGUMENT_QUESTION)!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_question_txt.text = question.question

        btnOption1.apply {
            text = question.options[0]
            setOnClickListener { selectButton(1) }
        }
        btnOption2.apply {
            text = question.options[1]
            setOnClickListener { selectButton(2) }
        }
        btnOption3.apply {
            text = question.options[2]
            setOnClickListener { selectButton(3) }
        }
        btnOption4.apply {
            text = question.options[3]
            setOnClickListener { selectButton(4) }
        }
    }

    companion object {
        private const val ARGUMENT_QUESTION = "ARGUMENT_QUESTION"

        fun newInstance(question: Question): QuestionFragment =
                QuestionFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARGUMENT_QUESTION, question)
                    }
                }
    }

    private fun selectButton(button: Int) {
        when (button) {
            1 -> {
                btnOption1.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                btnOption2.setBackgroundResource(R.drawable.button_secondary_bg)
                btnOption3.setBackgroundResource(R.drawable.button_secondary_bg)
                btnOption4.setBackgroundResource(R.drawable.button_secondary_bg)
            }
            2 -> {
                btnOption2.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                btnOption1.setBackgroundResource(R.drawable.button_secondary_bg)
                btnOption3.setBackgroundResource(R.drawable.button_secondary_bg)
                btnOption4.setBackgroundResource(R.drawable.button_secondary_bg)
            }
            3 -> {
                btnOption3.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                btnOption2.setBackgroundResource(R.drawable.button_secondary_bg)
                btnOption1.setBackgroundResource(R.drawable.button_secondary_bg)
                btnOption4.setBackgroundResource(R.drawable.button_secondary_bg)
            }
            4 -> {
                btnOption4.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                btnOption2.setBackgroundResource(R.drawable.button_secondary_bg)
                btnOption3.setBackgroundResource(R.drawable.button_secondary_bg)
                btnOption1.setBackgroundResource(R.drawable.button_secondary_bg)
            }
        }
    }

}
