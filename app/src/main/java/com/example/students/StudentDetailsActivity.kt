package com.example.students

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.students.model.StudentRepository

class StudentDetailsActivity : AppCompatActivity() {
    
    private var studentId: String? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)
        
        studentId = intent.getStringExtra("STUDENT_ID")
        
        if (studentId == null) {
            finish()
            return
        }
        
        loadStudentDetails()
        
        // Setup edit button
        findViewById<Button>(R.id.buttonEdit).setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("STUDENT_ID", studentId)
            startActivity(intent)
        }
        
        // Setup home button
        findViewById<Button>(R.id.buttonHome).setOnClickListener {
            // Go back to MainActivity, clearing the activity stack
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
    
    override fun onResume() {
        super.onResume()
        loadStudentDetails()
    }
    
    private fun loadStudentDetails() {
        val student = studentId?.let { StudentRepository.getStudent(it) } ?: return
        
        // Load student details into UI elements
        findViewById<TextView>(R.id.textViewDetailName).text = student.name
        findViewById<TextView>(R.id.textViewDetailId).text = student.studentId
        findViewById<TextView>(R.id.textViewDetailPhone).text = student.phone
        findViewById<TextView>(R.id.textViewDetailAddress).text = student.address
        findViewById<ImageView>(R.id.imageViewDetailProfile).setImageResource(R.drawable.student_profile)
    }
} 