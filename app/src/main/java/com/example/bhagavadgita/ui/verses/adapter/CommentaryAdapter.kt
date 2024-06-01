package com.example.bhagavadgita.ui.verses.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bhagavadgita.data.model.Commentary
import com.example.bhagavadgita.R
import java.util.Locale

class CommentaryAdapter(var commentaries: ArrayList<Commentary>, var context: Context) :
    RecyclerView.Adapter<CommentaryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_single_verses, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvDesc.text = commentaries[position].getDescription().trim { it <= ' ' }
        holder.tvLanguage.text = commentaries[position].getLanguage().uppercase(Locale.getDefault())
        holder.tvAuthor.text = commentaries[position].getAuthor_name()
    }

    override fun getItemCount(): Int {
        return commentaries.size
    }

    fun addData(commentaries: ArrayList<Commentary>) {
        this.commentaries.addAll(commentaries)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDesc: TextView
        var tvAuthor: TextView
        var tvLanguage: TextView

        init {
            tvDesc = itemView.findViewById(R.id.tvDesc)
            tvAuthor = itemView.findViewById(R.id.tvAuthor)
            tvLanguage = itemView.findViewById(R.id.tvLanguage)
        }
    }
}
