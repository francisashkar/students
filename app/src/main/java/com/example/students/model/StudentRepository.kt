package com.example.students.model

object StudentRepository {
    private val students = mutableListOf<Student>()

    init {
        // Add some example students
        addStudent(Student(name = "John Doe", studentId = "123456", phone = "555-1234", address = "123 Main St"))
        addStudent(Student(name = "Jane Smith", studentId = "654321", phone = "555-5678", address = "456 Oak Ave"))
        addStudent(Student(name = "Bob Johnson", studentId = "789012", phone = "555-9012", address = "789 Pine Rd"))
    }

    fun getAllStudents(): List<Student> {
        return students
    }

    fun getStudent(id: String): Student? {
        return students.find { it.id == id }
    }

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun updateStudent(student: Student) {
        val index = students.indexOfFirst { it.id == student.id }
        if (index != -1) {
            students[index] = student
        }
    }

    fun deleteStudent(id: String) {
        students.removeIf { it.id == id }
    }
} 