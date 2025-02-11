package com.example.simplemail

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class EmailAdapter(private val emails: List<Email>, private val context: Context) : RecyclerView.Adapter<EmailAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val senderTextView: TextView
        val titleTextView:  TextView
        val summaryTextView:  TextView
        val dateTextView: TextView

        init {

            senderTextView = itemView.findViewById<TextView>(R.id.senderTv)
            titleTextView = itemView.findViewById<TextView>(R.id.titleTv)
            summaryTextView = itemView.findViewById<TextView>(R.id.summaryTv)
            dateTextView = itemView.findViewById<TextView>(R.id.dateTv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.email_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return emails.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val email = emails.get(position)
        holder.senderTextView.text = email.sender
        holder.titleTextView.text = email.title
        holder.summaryTextView.text = email.summary
        holder.dateTextView.text = email.date

        val textStyle = if (email.isRead) Typeface.NORMAL else Typeface.BOLD
        holder.senderTextView.setTypeface(null, textStyle)
        holder.titleTextView.setTypeface(null, textStyle)

        holder.itemView.setOnClickListener {
            if (!email.isRead){
                email.isRead = true
                notifyItemChanged(position)
            }
            Toast.makeText(context, "Email opened", Toast.LENGTH_SHORT).show()
        }
    }
}