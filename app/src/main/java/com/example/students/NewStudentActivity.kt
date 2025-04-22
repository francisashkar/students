package com.example.students

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.students.model.Student
import com.example.students.model.StudentRepository

class NewStudentActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)
        
        setupSaveButton()
    }
    
    private fun setupSaveButton() {
        findViewById<Button>(R.id.buttonSave).setOnClickListener {
            if (validateForm()) {
                saveStudent()
                finish() // Return to previous screen
            }
        }
        
        findViewById<Button>(R.id.buttonCancel).setOnClickListener {
            finish() // Return to previous screen without saving
        }
        
        // Setup home button
        findViewById<Button>(R.id.buttonHomeNew).setOnClickListener {
            // Go back to MainActivity, clearing the activity stack
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
    
    private fun validateForm(): Boolean {
        val nameField = findViewById<EditText>(R.id.editTextName)
        val idField = findViewById<EditText>(R.id.editTextStudentId)
        
        if (nameField.text.toString().trim().isEmpty()) {
            nameField.error = "Name is required"
            return false
        }
        
        if (idField.text.toString().trim().isEmpty()) {
            idField.error = "Student ID is required"
            return false
        }
        
        return true
    }
    
    private fun saveStudent() {
        val student = Student(
            name = findViewById<EditText>(R.id.editTextName).text.toString(),
            studentId = findViewById<EditText>(R.id.editTextStudentId).text.toString(),
            phone = findViewById<EditText>(R.id.editTextPhone).text.toString(),
            address = findViewById<EditText>(R.id.editTextAddress).text.toString()
        )
        
        StudentRepository.addStudent(student)
        Toast.makeText(this, "Student saved successfully", Toast.LENGTH_SHORT).show()
    }
} 