package com.example.bhagavadgita.utils

import androidx.room.TypeConverter
import com.example.bhagavadgita.data.model.Translation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TranslationListConverter {

    @TypeConverter
    fun fromTranslationList(translations: ArrayList<Translation>?): String? {
        if (translations == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Translation>>() {}.type
        return gson.toJson(translations, type)
    }

    @TypeConverter
    fun toTranslationList(translationsString: String?): ArrayList<Translation>? {
        if (translationsString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Translation>>() {}.type
        return gson.fromJson(translationsString, type)
    }
}
