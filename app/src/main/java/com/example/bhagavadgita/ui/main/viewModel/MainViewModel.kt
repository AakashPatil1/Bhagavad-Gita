package com.example.bhagavadgita.ui.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bhagavadgita.data.model.Chapters
import com.example.bhagavadgita.data.repository.MainRepository
import com.example.bhagavadgita.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _chapters = MutableLiveData<Response<List<Chapters>>>()
    val chapters: LiveData<Response<List<Chapters>>>
        get() = _chapters

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                val res = mainRepository.getAllChapters()
                if (res.isSuccessful && res.body() != null) {
                    _chapters.postValue(res)
                }
            }
        }
    }

}
