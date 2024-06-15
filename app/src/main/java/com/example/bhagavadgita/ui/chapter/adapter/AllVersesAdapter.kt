package com.example.bhagavadgita.ui.chapter.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bhagavadgita.data.model.Verses
import com.example.bhagavadgita.R
import com.example.bhagavadgita.ui.verses.view.SingleVersesActivity

class AllVersesAdapter(var list: MutableList<Verses>, var vContext: Context) :
    RecyclerView.Adapter<AllVersesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_verses, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvText.text = list[position].text
        holder.itemView.setOnClickListener {
            val intent = Intent(vContext, SingleVersesActivity::class.java)
            intent.putExtra("chapterNumber", list[position].chapterNumber)
            intent.putExtra("versesNumber", list[position].verseNumber)
            vContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addData(list: List<Verses>) {
        this.list.addAll(list)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvText: TextView

        init {
            tvText = itemView.findViewById(R.id.tvText)
        }
    }
}
