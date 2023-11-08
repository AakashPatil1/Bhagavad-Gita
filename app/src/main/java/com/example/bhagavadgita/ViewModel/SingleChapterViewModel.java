package com.example.bhagavadgita.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bhagavadgita.Models.Chapters;
import com.example.bhagavadgita.Models.Verses;

import java.util.List;

public class SingleChapterViewModel extends ViewModel {

    private final MutableLiveData<Chapters> singleChaptersLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Verses>> getVersesLiveData = new MutableLiveData<>();

    public LiveData<Chapters> getChaptersSingleLiveData() {
        return singleChaptersLiveData;
    }

    public void setSingleChapter(Chapters chapters){
        singleChaptersLiveData.setValue(chapters);
    }


    public LiveData<List<Verses>> getVersesLiveData(){
        return getVersesLiveData;
    }

    public void setVersesLiveData(List<Verses> verses){
        getVersesLiveData.setValue(verses);
    }
}
