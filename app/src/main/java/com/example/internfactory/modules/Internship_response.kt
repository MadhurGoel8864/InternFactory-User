package com.example.internfactory.modules

data class Internship_response(
    val content: List<ContentX>,
    val lastPage: Boolean,
    val pageNumber: Int,
    val pageSize: Int,
    val totalElements: Int,
    val totalPage: Int
)