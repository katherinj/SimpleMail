package com.example.simplemail

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var emails: MutableList<Email>
    private lateinit var emailAdapter: EmailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val emailsRecyclerView = findViewById<RecyclerView>(R.id.emailsRv)
        emails = EmailFetcher.getEmails()

        emailAdapter = EmailAdapter(emails, this)

        emailsRecyclerView.adapter = emailAdapter
        emailsRecyclerView.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.loadMoreBtn).setOnClickListener {
            val newEmails = EmailFetcher.getNext5Emails()
            val startPosition = emails.size
            emails.addAll(newEmails)
            emailAdapter.notifyItemRangeInserted(startPosition, newEmails.size)        }
    }
}