package com.quadriyanney.stanbicchallenge


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.card_layout.*


class QuestionFragment : Fragment() {

    lateinit var question: Question

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        question = arguments!!.getParcelable("question")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_question_txt.text = question.question

        btn_option_1.apply {
            text = question.options[0]
            setOnClickListener { selectButton(1) }
        }
        btn_option_2.apply {
            text = question.options[1]
            setOnClickListener { selectButton(2) }
        }
        btn_option_3.apply {
            text = question.options[2]
            setOnClickListener { selectButton(3) }
        }
        btn_option_4.apply {
            text = question.options[3]
            setOnClickListener { selectButton(4) }
        }
    }

    companion object {
        fun newInstance(question: Question): QuestionFragment {
            val fragment = QuestionFragment()
            val bundle = Bundle()
            bundle.putParcelable("question", question)
            fragment.arguments = bundle
            return fragment
        }
    }

    private fun selectButton(button: Int) {
        when(button){
            1 -> {
                btn_option_1.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                btn_option_2.setBackgroundResource(R.drawable.button_secondary_bg)
                btn_option_3.setBackgroundResource(R.drawable.button_secondary_bg)
                btn_option_4.setBackgroundResource(R.drawable.button_secondary_bg)
            }
            2 -> {
                btn_option_2.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                btn_option_1.setBackgroundResource(R.drawable.button_secondary_bg)
                btn_option_3.setBackgroundResource(R.drawable.button_secondary_bg)
                btn_option_4.setBackgroundResource(R.drawable.button_secondary_bg)
            }
            3 -> {
                btn_option_3.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                btn_option_2.setBackgroundResource(R.drawable.button_secondary_bg)
                btn_option_1.setBackgroundResource(R.drawable.button_secondary_bg)
                btn_option_4.setBackgroundResource(R.drawable.button_secondary_bg)
            }
            4 -> {
                btn_option_4.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                btn_option_2.setBackgroundResource(R.drawable.button_secondary_bg)
                btn_option_3.setBackgroundResource(R.drawable.button_secondary_bg)
                btn_option_1.setBackgroundResource(R.drawable.button_secondary_bg)
            }
        }
    }

}
