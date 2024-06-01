package com.example.bhagavadgita.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Verses {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("verse_number")
    @Expose
    public int verse_number;
    @SerializedName("chapter_number")
    @Expose
    public int chapter_number;
    @SerializedName("slug")
    @Expose
    public String slug;
    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("transliteration")
    @Expose
    public String transliteration;
    @SerializedName("word_meanings")
    @Expose
    public String word_meanings;
    @SerializedName("translations")
    @Expose
    public ArrayList<Translation> translations;
    @SerializedName("commentaries")
    @Expose
    public ArrayList<Commentary> commentaries;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVerse_number() {
        return verse_number;
    }

    public void setVerse_number(int verse_number) {
        this.verse_number = verse_number;
    }

    public int getChapter_number() {
        return chapter_number;
    }

    public void setChapter_number(int chapter_number) {
        this.chapter_number = chapter_number;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTransliteration() {
        return transliteration;
    }

    public void setTransliteration(String transliteration) {
        this.transliteration = transliteration;
    }

    public String getWord_meanings() {
        return word_meanings;
    }

    public void setWord_meanings(String word_meanings) {
        this.word_meanings = word_meanings;
    }

    public ArrayList<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(ArrayList<Translation> translations) {
        this.translations = translations;
    }

    public ArrayList<Commentary> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(ArrayList<Commentary> commentaries) {
        this.commentaries = commentaries;
    }
}
