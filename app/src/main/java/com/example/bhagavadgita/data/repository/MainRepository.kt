package com.example.bhagavadgita.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bhagavadgita.data.api.ApiHelper
import com.example.bhagavadgita.data.model.Chapters
import com.example.bhagavadgita.roomdb.BhagavadGitaDao
import com.example.bhagavadgita.utils.NetworkHelper
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val networkHelper: NetworkHelper,
    private val bhagavadGitaDao: BhagavadGitaDao
) {

    private val _chapters = MutableLiveData<List<Chapters>>()
    val chapters: LiveData<List<Chapters>>
        get() = _chapters

    suspend fun getAllChapters() {
        if (networkHelper.isNetworkConnected()) {
            val response = apiHelper.getAllChapters()
            if (response.isSuccessful && response.body() != null) {
                val bodyData = response.body()
                bhagavadGitaDao.insertChapters(bodyData!!)
                val data: LiveData<List<Chapters>> = bhagavadGitaDao.getChapters()
                data.observeForever { chapters ->
                    if (chapters != null && chapters.isNotEmpty()) {
                        _chapters.postValue(chapters)
                    }
                }
            }
        } else {
            val data: LiveData<List<Chapters>> = bhagavadGitaDao.getChapters()
            data.observeForever { chapters ->
                if (chapters != null && chapters.isNotEmpty()) {
                    _chapters.postValue(chapters)
                }
            }
        }
    }
}