package com.example.workoutplanner

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddWorkoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_workout)

        val dbHelper = WorkoutDbHelper(this)
        val nameField = findViewById<EditText>(R.id.nameField)
        val equipmentField = findViewById<EditText>(R.id.equipmentField)
        val setsField = findViewById<EditText>(R.id.setsField)
        val repsField = findViewById<EditText>(R.id.repsField)
        val weightField = findViewById<EditText>(R.id.weightField)
        val saveButton = findViewById<Button>(R.id.saveButton)

        saveButton.setOnClickListener {
            dbHelper.insertWorkout(
                nameField.text.toString(),
                equipmentField.text.toString(),
                setsField.text.toString().toInt(),
                repsField.text.toString().toInt(),
                weightField.text.toString().toFloat()
            )
            finish()
        }
    }
}