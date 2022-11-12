package com.example.internfactory.modules

data class SearchingResponse(
    val content: List<Content>,
    val lastPage: Boolean,
    val pageNumber: Int,
    val pageSize: Int,
    val totalElements: Int,
    val totalPage: Int
)