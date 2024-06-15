package com.example.bhagavadgita.ui.verses.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bhagavadgita.data.model.Translation
import com.example.bhagavadgita.R
import com.google.gson.Gson
import java.util.Locale

class TranslationAdapter(var translations: ArrayList<Translation>, var context: Context) :
    RecyclerView.Adapter<TranslationAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_single_verses, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvDesc.text = translations[position].description
        holder.tvLanguage.text = translations[position].language!!.uppercase(Locale.getDefault())
        holder.tvAuthor.text = translations[position].author_name
    }

    override fun getItemCount(): Int {
        return translations.size
    }

    fun addData(translations: ArrayList<Translation>) {
        this.translations.addAll(translations)
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
