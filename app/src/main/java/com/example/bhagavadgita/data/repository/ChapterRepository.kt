package com.example.bhagavadgita.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bhagavadgita.data.api.ApiHelper
import com.example.bhagavadgita.data.model.Chapters
import com.example.bhagavadgita.data.model.Verses
import com.example.bhagavadgita.roomdb.BhagavadGitaDao
import com.example.bhagavadgita.utils.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChapterRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val networkHelper: NetworkHelper,
    private val bhagavadGitaDao: BhagavadGitaDao
) {

    private val _singleChaptersLiveData = MutableLiveData<Chapters>()
    private val _getVersesLiveData = MutableLiveData<List<Verses>>()
    val chaptersSingleLiveData: LiveData<Chapters>
        get() = _singleChaptersLiveData

    val versesLiveData: LiveData<List<Verses>>
        get() = _getVersesLiveData

    suspend fun getParticularChapter(chapterNumber: Int) {
        withContext(Dispatchers.IO) {
            val data: Chapters = bhagavadGitaDao.getParticularChapter(chapterNumber)
            _singleChaptersLiveData.postValue(data)
        }
        if (networkHelper.isNetworkConnected()) {
            val resData = apiHelper.getAllVersesFromParticularChapter(chapterNumber)
            if (resData.isSuccessful && resData.body() != null) {
                val data = resData.body()
                bhagavadGitaDao.insertVerses(data!!)
                val list: LiveData<List<Verses>> = bhagavadGitaDao.getVerses()
                list.observeForever { verses ->
                    _getVersesLiveData.postValue(verses)
                }
            }
        } else {
            val list: LiveData<List<Verses>> = bhagavadGitaDao.getVerses()
            list.observeForever { verses ->
                _getVersesLiveData.postValue(verses)
            }
        }

    }


}