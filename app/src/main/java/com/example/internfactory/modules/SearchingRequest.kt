package com.example.internfactory.modules

data class SearchingRequest(
    val pageNumber: Int,
    val pageSize: Int,
    val sortBy: String,
    val sortDir: String
)