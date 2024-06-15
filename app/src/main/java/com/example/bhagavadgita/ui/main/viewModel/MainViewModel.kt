package com.example.bhagavadgita.ui.main.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bhagavadgita.data.model.Chapters
import com.example.bhagavadgita.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    ) : ViewModel() {

    private val _chapters = MutableLiveData<List<Chapters>>()
    val chapters: LiveData<List<Chapters>>
        get() = _chapters

    init {
        fetchData()
    }


    private fun fetchData() {
        viewModelScope.launch {

            mainRepository.getAllChapters()
            mainRepository.chapters.observeForever { data ->
                _chapters.postValue(data)
            }
        }
    }

}
