package com.example.students

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.students.model.Student
import com.example.students.model.StudentRepository

class StudentAdapter(
    private val onItemClick: (Student) -> Unit,
    private val onCheckChanged: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var students = StudentRepository.getAllStudents()

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageProfile: ImageView = itemView.findViewById(R.id.imageViewProfile)
        val textName: TextView = itemView.findViewById(R.id.textViewName)
        val textId: TextView = itemView.findViewById(R.id.textViewId)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBoxStudent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        
        holder.textName.text = student.name
        holder.textId.text = student.studentId
        holder.checkBox.isChecked = student.isChecked
        
        holder.itemView.setOnClickListener {
            onItemClick(student)
        }
        
        holder.checkBox.setOnClickListener {
            student.isChecked = holder.checkBox.isChecked
            onCheckChanged(student)
        }
    }

    override fun getItemCount(): Int = students.size

    fun updateData() {
        students = StudentRepository.getAllStudents()
        notifyDataSetChanged()
    }
} 