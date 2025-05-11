package com.example.workoutplanner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WorkoutAdapter(
    private var workoutList: List<Workout>,
    private val onDelete: (Workout) -> Unit
) : RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

    inner class WorkoutViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val infoText: TextView = view.findViewById(R.id.infoText)
        val doneButton: Button = view.findViewById(R.id.doneButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.workout_item, parent, false)
        return WorkoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val workout = workoutList[position]
        holder.infoText.text = "${workout.name} - ${workout.equipment}\nSets: ${workout.sets}, Reps: ${workout.reps}, Weight: ${workout.weight}kg"
        holder.doneButton.setOnClickListener { onDelete(workout) }
    }

    override fun getItemCount() = workoutList.size

    fun updateList(newList: List<Workout>) {
        workoutList = newList
        notifyDataSetChanged()
    }
}