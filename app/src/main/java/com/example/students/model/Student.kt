package com.example.students.model

import java.util.UUID

data class Student(
    var id: String = UUID.randomUUID().toString(),
    var name: String = "",
    var studentId: String = "",
    var phone: String = "",
    var address: String = "",
    var isChecked: Boolean = false
) 