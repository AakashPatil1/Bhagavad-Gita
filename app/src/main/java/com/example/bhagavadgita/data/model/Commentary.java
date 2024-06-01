package com.example.bhagavadgita.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Commentary {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("author_name")
    @Expose
    public String author_name;
    @SerializedName("language")
    @Expose
    public String language;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}