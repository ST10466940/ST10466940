package com.example.mealsuggestionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    // Declare UI components
    private lateinit var timeInput: EditText
    private lateinit var suggestionOutput: TextView
    private lateinit var suggestButton: Button
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        timeInput = findViewById(R.id.timeInput)
        suggestionOutput = findViewById(R.id.suggestionOutput)
        suggestButton = findViewById(R.id.suggestButton)
        resetButton = findViewById(R.id.resetButton)

        // Set click listeners
        suggestButton.setOnClickListener { suggestMeal() }
        resetButton.setOnClickListener { resetInputs() }
    }

    /**
     * Function to suggest a meal based on the time of day input
     */
    private fun suggestMeal() {
        // Get user input and convert to lowercase for case-insensitive comparison
        val timeOfDay = timeInput.text.toString().trim().lowercase()

        // Validate input is not empty
        if (timeOfDay.isEmpty()) {
            showError("Please enter a time of day (e.g., Morning, Afternoon)")
            return
        }

        // Determine meal suggestion based on time of day
        val suggestion = when (timeOfDay) {
            "morning" -> "Breakfast: How about some scrambled eggs with whole wheat toast?"
            "mid-morning" -> "Mid-morning snack: A banana or some mixed nuts would be great!"
            "afternoon" -> "Lunch: Try a turkey and avocado sandwich with a side salad."
            "mid-afternoon" -> "Afternoon snack: Greek yogurt with honey and berries."
            "dinner" -> "Dinner: Grilled salmon with roasted vegetables and quinoa."
            else -> {
                showError("Invalid input. Please enter one of: Morning, Mid-morning, Afternoon, Mid-afternoon, Dinner")
                return
            }
        }

        // Display the suggestion
        suggestionOutput.text = suggestion
        suggestionOutput.visibility = View.VISIBLE
    }

    /**
     * Function to show error messages to the user
     * @param message The error message to display
     */
    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        suggestionOutput.visibility = View.INVISIBLE
    }

    /**
     * Function to reset all inputs and outputs
     */
    private fun resetInputs() {
        timeInput.text.clear()
        suggestionOutput.text = ""
        suggestionOutput.visibility = View.INVISIBLE
    }
}