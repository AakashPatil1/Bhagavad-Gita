package com.example.bhagavadgita.ui.verses.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bhagavadgita.data.model.Verses
import com.example.bhagavadgita.data.repository.VersesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleVersesViewModel @Inject constructor(private val versesRepository: VersesRepository) :
    ViewModel() {
    private val _versesLiveData = MutableLiveData<Verses>()
    val verses: LiveData<Verses>
        get() = _versesLiveData


    fun fetchSingleVerses(chapterNumber: Int, versesNumber: Int) {
        viewModelScope.launch {
            versesRepository.getParticularVerse(chapterNumber, versesNumber)
            versesRepository.verses.observeForever {
                _versesLiveData.postValue(it)
            }
        }
    }

}
