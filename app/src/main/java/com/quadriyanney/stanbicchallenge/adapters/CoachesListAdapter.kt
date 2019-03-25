package com.quadriyanney.stanbicchallenge.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quadriyanney.stanbicchallenge.R
import com.quadriyanney.stanbicchallenge.commons.Utils
import com.quadriyanney.stanbicchallenge.model.Coach
import kotlinx.android.synthetic.main.item_coach.view.*

class CoachesListAdapter(private val listener: InteractionListener) :
        RecyclerView.Adapter<CoachesListAdapter.ViewHolder>() {

    private val coaches = Utils.getTestCoaches()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_coach, parent, false))

    override fun getItemCount(): Int = coaches.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(coaches[position])

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(coach: Coach) {
            view.coach_name.text = coach.name
            view.coach_info.setOnClickListener { listener.showCoachDetails(coach) }
            view.setOnClickListener { listener.showQuestions(coaches[adapterPosition]) }
        }
    }

    interface InteractionListener {
        fun showCoachDetails(coach: Coach)
        fun showQuestions(coach: Coach)
    }
}