package com.example.bhagavadgita.Local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bhagavadgita.Models.Chapters;
import com.example.bhagavadgita.Models.Verses;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesManager {

    private static final String PREF_NAME = "MyPreferences";
    private static final String KEY_CHAPTERS = "chapters";
    private static final String KEY_VERSES = "verses";


    public static void saveListChapter(Context context, List<Chapters> value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String chaptersJson = GsonHelper.toJson(value);
        editor.putString(KEY_CHAPTERS, chaptersJson);
        editor.apply();
    }

    public static void saveListVerses(Context context, List<Verses> value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String versesJson = GsonHelper.toJson(value);
        editor.putString(KEY_VERSES, versesJson);
        editor.apply();
    }

    public static List<Chapters> getChapters(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String chaptersJson = sharedPreferences.getString(KEY_CHAPTERS, null);

        if (chaptersJson != null) {
            Type listType = new TypeToken<ArrayList<Chapters>>() {
            }.getType();
            return GsonHelper.fromJsonList(chaptersJson, listType);
        }

        return new ArrayList<>();
    }

    public static Chapters getChapterByNumber(Context context, int chapterNumber) {
        List<Chapters> chapters = getChapters(context);
        for (Chapters chapter : chapters) {
            if (chapter.getChapterNumber() == chapterNumber) {
                return chapter;
            }
        }
        return null;
    }

    public static List<Verses> getVerses(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String versesJson = sharedPreferences.getString(KEY_VERSES, null);

        if (versesJson != null) {
            Type listType = new TypeToken<ArrayList<Verses>>() {
            }.getType();
            return GsonHelper.fromJsonList(versesJson, listType);
        }

        return new ArrayList<>();
    }

    public static Verses getVerseByChapterAndVerseNumber(Context context, int chapterNumber, int verseNumber) {
        List<Verses> verses = getVerses(context);
        for (Verses verse : verses) {
            if (verse.getChapter_number() == chapterNumber && verse.getVerse_number() == verseNumber) {
                return verse;
            }
        }
        return null;
    }

}
