package com.example.bhagavadgita.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chapters {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("name_transliterated")
    @Expose
    private String nameTransliterated;
    @SerializedName("name_translated")
    @Expose
    private String nameTranslated;
    @SerializedName("verses_count")
    @Expose
    private Integer versesCount;
    @SerializedName("chapter_number")
    @Expose
    private Integer chapterNumber;
    @SerializedName("name_meaning")
    @Expose
    private String nameMeaning;
    @SerializedName("chapter_summary")
    @Expose
    private String chapterSummary;
    @SerializedName("chapter_summary_hindi")
    @Expose
    private String chapterSummaryHindi;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getNameTransliterated() {
        return nameTransliterated;
    }

    public void setNameTransliterated(String nameTransliterated) {
        this.nameTransliterated = nameTransliterated;
    }

    public String getNameTranslated() {
        return nameTranslated;
    }

    public void setNameTranslated(String nameTranslated) {
        this.nameTranslated = nameTranslated;
    }

    public Integer getVersesCount() {
        return versesCount;
    }

    public void setVersesCount(Integer versesCount) {
        this.versesCount = versesCount;
    }

    public Integer getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(Integer chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getNameMeaning() {
        return nameMeaning;
    }

    public void setNameMeaning(String nameMeaning) {
        this.nameMeaning = nameMeaning;
    }

    public String getChapterSummary() {
        return chapterSummary;
    }

    public void setChapterSummary(String chapterSummary) {
        this.chapterSummary = chapterSummary;
    }

    public String getChapterSummaryHindi() {
        return chapterSummaryHindi;
    }

    public void setChapterSummaryHindi(String chapterSummaryHindi) {
        this.chapterSummaryHindi = chapterSummaryHindi;
    }
}
