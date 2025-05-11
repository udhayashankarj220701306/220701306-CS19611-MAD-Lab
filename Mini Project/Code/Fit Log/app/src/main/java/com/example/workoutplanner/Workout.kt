package com.example.workoutplanner

data class Workout(
    val id: Int,
    val name: String,
    val equipment: String,
    val sets: Int,
    val reps: Int,
    val weight: Float
)