package com.example.workoutplanner

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class WorkoutDbHelper(context: Context) : SQLiteOpenHelper(context, "workouts.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE workouts (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, equipment TEXT, sets INTEGER, reps INTEGER, weight REAL)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS workouts")
        onCreate(db)
    }

    fun insertWorkout(name: String, equipment: String, sets: Int, reps: Int, weight: Float) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", name)
            put("equipment", equipment)
            put("sets", sets)
            put("reps", reps)
            put("weight", weight)
        }
        db.insert("workouts", null, values)
    }

    fun getAllWorkouts(): List<Workout> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM workouts", null)
        val list = mutableListOf<Workout>()
        while (cursor.moveToNext()) {
            list.add(
                Workout(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getFloat(5)
                )
            )
        }
        cursor.close()
        return list
    }

    fun deleteWorkout(id: Int) {
        writableDatabase.delete("workouts", "id=?", arrayOf(id.toString()))
    }
}