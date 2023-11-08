package com.example.bhagavadgita.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bhagavadgita.Models.Verses;

import java.util.List;

public class SingleVersesViewModel extends ViewModel {

    private final MutableLiveData<Verses> versesLiveData = new MutableLiveData<>();

    public LiveData<Verses> getVerses(){
        return versesLiveData;
    }


    public void setVerses(Verses verses){
        versesLiveData.setValue(verses);
    }
}
