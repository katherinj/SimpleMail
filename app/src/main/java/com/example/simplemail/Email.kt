package com.example.simplemail

class Email (
    val sender: String,
    val title: String,
    val summary: String,
    val date: String,
    var isRead: Boolean = false
) {

}