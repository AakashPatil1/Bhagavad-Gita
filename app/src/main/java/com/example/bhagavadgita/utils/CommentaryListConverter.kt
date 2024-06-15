package com.example.bhagavadgita.utils

import androidx.room.TypeConverter
import com.example.bhagavadgita.data.model.Commentary
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CommentaryListConverter {

    @TypeConverter
    fun fromCommentaryList(commentaries: ArrayList<Commentary>?): String? {
        if (commentaries == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Commentary>>() {}.type
        return gson.toJson(commentaries, type)
    }

    @TypeConverter
    fun toCommentaryList(commentariesString: String?): ArrayList<Commentary>? {
        if (commentariesString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Commentary>>() {}.type
        return gson.fromJson(commentariesString, type)
    }
}
