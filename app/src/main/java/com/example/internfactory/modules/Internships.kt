package com.example.internfactory.modules

data class Internships(
    val about: String,
    val category: Category,
    val id: Int,
    val imageUrl: String,
    val issuedDate: Long,
    val lastDate: Long,
    val perks: String,
    val skills: String,
    val stipend: String,
    val tenure: String,
    val title: String,
    val type: String,
    val who_can_apply: String
)