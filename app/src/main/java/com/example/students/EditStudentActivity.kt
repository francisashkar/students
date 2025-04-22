package com.example.students

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.students.model.Student
import com.example.students.model.StudentRepository

class EditStudentActivity : AppCompatActivity() {
    
    private var studentId: String? = null
    private var student: Student? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)
        
        studentId = intent.getStringExtra("STUDENT_ID")
        
        if (studentId == null) {
            finish()
            return
        }
        
        student = StudentRepository.getStudent(studentId!!)
        
        if (student == null) {
            Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        
        populateForm()
        setupButtons()
    }
    
    private fun populateForm() {
        student?.let { student ->
            findViewById<EditText>(R.id.editTextNameEdit).setText(student.name)
            findViewById<EditText>(R.id.editTextStudentIdEdit).setText(student.studentId)
            findViewById<EditText>(R.id.editTextPhoneEdit).setText(student.phone)
            findViewById<EditText>(R.id.editTextAddressEdit).setText(student.address)
        }
    }
    
    private fun setupButtons() {
        findViewById<Button>(R.id.buttonUpdate).setOnClickListener {
            if (validateForm()) {
                updateStudent()
                finish()
            }
        }
        
        findViewById<Button>(R.id.buttonDelete).setOnClickListener {
            showDeleteConfirmationDialog()
        }
        
        findViewById<Button>(R.id.buttonCancelEdit).setOnClickListener {
            finish()
        }
        
        // Setup home button
        findViewById<Button>(R.id.buttonHomeEdit).setOnClickListener {
            // Go back to MainActivity, clearing the activity stack
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
    
    private fun validateForm(): Boolean {
        val nameField = findViewById<EditText>(R.id.editTextNameEdit)
        val idField = findViewById<EditText>(R.id.editTextStudentIdEdit)
        
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
    
    private fun updateStudent() {
        student?.let { currentStudent ->
            val updatedStudent = Student(
                id = currentStudent.id,
                name = findViewById<EditText>(R.id.editTextNameEdit).text.toString(),
                studentId = findViewById<EditText>(R.id.editTextStudentIdEdit).text.toString(),
                phone = findViewById<EditText>(R.id.editTextPhoneEdit).text.toString(),
                address = findViewById<EditText>(R.id.editTextAddressEdit).text.toString(),
                isChecked = currentStudent.isChecked
            )
            
            StudentRepository.updateStudent(updatedStudent)
            Toast.makeText(this, "Student updated successfully", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun showDeleteConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Delete Student")
            .setMessage("Are you sure you want to delete this student?")
            .setPositiveButton("Delete") { _, _ ->
                deleteStudent()
                finish()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun deleteStudent() {
        studentId?.let {
            StudentRepository.deleteStudent(it)
            Toast.makeText(this, "Student deleted successfully", Toast.LENGTH_SHORT).show()
        }
    }
} 