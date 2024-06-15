package com.example.bhagavadgita.ui.chapter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bhagavadgita.data.model.Chapters
import com.example.bhagavadgita.data.model.Verses
import com.example.bhagavadgita.data.repository.ChapterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleChapterViewModel @Inject constructor(private val chapterRepository: ChapterRepository) :
    ViewModel() {
    private val _singleChaptersLiveData = MutableLiveData<Chapters>()
    private val _getVersesLiveData = MutableLiveData<List<Verses>>()
    val chaptersSingleLiveData: LiveData<Chapters>
        get() = _singleChaptersLiveData

    val versesLiveData: LiveData<List<Verses>>
        get() = _getVersesLiveData

    fun fetchData(chapterNumber: Int) {
        viewModelScope.launch {
            chapterRepository.getParticularChapter(chapterNumber)
            chapterRepository.chaptersSingleLiveData.observeForever { chapter ->
                _singleChaptersLiveData.postValue(chapter)
            }
            chapterRepository.versesLiveData.observeForever { verses ->
                _getVersesLiveData.postValue(verses)
            }
        }
    }

}
