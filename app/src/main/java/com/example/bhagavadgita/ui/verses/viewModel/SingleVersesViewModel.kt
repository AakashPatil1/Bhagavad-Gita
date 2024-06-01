package com.example.bhagavadgita.ui.verses.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bhagavadgita.data.model.Verses
import com.example.bhagavadgita.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleVersesViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {
    private val _versesLiveData = MutableLiveData<Verses>()
    val verses: LiveData<Verses>
        get() = _versesLiveData


    fun fetchSingleVerses(chapterNumber: Int, versesNumber: Int) {
        viewModelScope.launch {
            val res = mainRepository.getParticularVerse(chapterNumber, versesNumber)
            if (res.isSuccessful && res.body() != null){
                _versesLiveData.postValue(res.body())
            }
        }
    }

}
