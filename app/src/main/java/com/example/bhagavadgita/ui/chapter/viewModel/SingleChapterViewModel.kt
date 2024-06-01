package com.example.bhagavadgita.ui.chapter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bhagavadgita.data.model.Chapters
import com.example.bhagavadgita.data.model.Verses
import com.example.bhagavadgita.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleChapterViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    private val _singleChaptersLiveData = MutableLiveData<Chapters>()
    private val _getVersesLiveData = MutableLiveData<List<Verses>>()
    val chaptersSingleLiveData: LiveData<Chapters>
        get() = _singleChaptersLiveData

    val versesLiveData: LiveData<List<Verses>>
        get() = _getVersesLiveData

    public fun fetchData(chapterNumber: Int) {
        viewModelScope.launch {
            val res =mainRepository.getParticularChapter(chapterNumber)
            if (res.isSuccessful && res.body() != null){
                _singleChaptersLiveData.postValue(res.body())
            }
        }
    }

    public fun fetchVersesData(chapterNumber: Int){
        viewModelScope.launch {
            val res = mainRepository.getAllVersesFromParticularChapter(chapterNumber)
            if (res.isSuccessful && res.body() != null){
                _getVersesLiveData.postValue(res.body())
            }
        }
    }
}
