package com.example.bhagavadgita.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bhagavadgita.data.model.Chapters
import com.example.bhagavadgita.R
import com.example.bhagavadgita.ui.chapter.view.SingleChapterActivity

class AllChaptersAdapter(var list: ArrayList<Chapters>, var cContext: Context) :
    RecyclerView.Adapter<AllChaptersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_chapters, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = list[position].name
        holder.tvNameTranslated.text = list[position].nameTranslated
        holder.tvChapterNo.text = list[position].chapterNumber.toString()
        holder.itemView.setOnClickListener {
            val intent = Intent(cContext, SingleChapterActivity::class.java)
            intent.putExtra("chapterNumber", list[position].chapterNumber)
            cContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView
        var tvNameTranslated: TextView
        var tvChapterNo: TextView

        init {
            tvName = itemView.findViewById(R.id.tvName)
            tvNameTranslated = itemView.findViewById(R.id.tvNameTranslated)
            tvChapterNo = itemView.findViewById(R.id.tvChapterNo)
        }
    }

    fun addData(newList: List<Chapters>) {
        list.addAll(newList)
    }
}
