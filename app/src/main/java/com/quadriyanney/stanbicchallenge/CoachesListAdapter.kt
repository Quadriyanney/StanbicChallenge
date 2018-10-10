package com.quadriyanney.stanbicchallenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_coach.view.*

class CoachesListAdapter(val listener: InteractionListener):
        RecyclerView.Adapter<CoachesListAdapter.ViewHolder>() {

    private val coaches = arrayListOf(
            Coach("Fred", "Banking and Personal Finance"),
            Coach("Yemi", "Asset and Wealth Management"),
            Coach("Cyril", "Capital and Investment"),
            Coach("Elise", "Stockbroking and Insurance"))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_coach, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = coaches.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(coaches[position])

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun bind(coach: Coach) {
            view.coach_name.text = coach.name
            view.coach_info.setOnClickListener { listener.showCoachDetails(coach) }
            view.setOnClickListener {
                listener.showQuestions(coaches[adapterPosition])
            }
        }
    }

    interface InteractionListener {
        fun showCoachDetails(coach: Coach)
        fun showQuestions(coach: Coach)
    }
}