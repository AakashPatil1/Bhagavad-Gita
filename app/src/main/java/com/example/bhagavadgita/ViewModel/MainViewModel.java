package com.example.bhagavadgita.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.bhagavadgita.Models.Chapters;

import java.util.List;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<List<Chapters>> chaptersLiveData = new MutableLiveData<>();

    public MainViewModel() {

    }

    public LiveData<List<Chapters>> getChaptersLiveData() {
        return chaptersLiveData;
    }

    public void setChapters(List<Chapters> chapters) {
        chaptersLiveData.setValue(chapters);
    }




}
