package com.quadriyanney.stanbicchallenge


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_coaches.*


class CoachesFragment : Fragment(), CoachesListAdapter.InteractionListener {

    private lateinit var adapter: CoachesListAdapter
    private lateinit var mListener: FragmentInteractionListener
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        username = arguments!!.getString("username")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coaches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val string = "Welcome $username, ${welcome.text}"
        welcome.text = string
        adapter = CoachesListAdapter(this)
        rv_coaches.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_coaches.adapter = adapter
    }

    override fun showCoachDetails(coach: Coach) {
        AlertDialog.Builder(context!!).setMessage("Meet ${coach.name}, \n\n " +
                "He'll be there to guide you through your ${coach.details} path.")
                .create()
                .show()
    }

    override fun showQuestions(coach: Coach) {
        mListener.showQuestions(coach)
    }

    fun setListener(listener: FragmentInteractionListener) {
        mListener = listener
    }

    interface FragmentInteractionListener {
        fun showQuestions(coach: Coach)
    }

    companion object {
        fun newInstance(username: String): CoachesFragment {
            val fragment = CoachesFragment()
            val bundle = Bundle()
            bundle.putString("username", username)
            fragment.arguments = bundle
            return fragment
        }
    }

}
