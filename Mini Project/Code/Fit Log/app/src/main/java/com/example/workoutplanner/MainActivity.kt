package com.example.workoutplanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: WorkoutDbHelper
    private lateinit var adapter: WorkoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = WorkoutDbHelper(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val addButton = findViewById<Button>(R.id.addButton)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = WorkoutAdapter(dbHelper.getAllWorkouts()) { workout ->
            dbHelper.deleteWorkout(workout.id)
            refreshList()
        }
        recyclerView.adapter = adapter

        addButton.setOnClickListener {
            startActivity(Intent(this, AddWorkoutActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }

    private fun refreshList() {
        adapter.updateList(dbHelper.getAllWorkouts())
    }
}