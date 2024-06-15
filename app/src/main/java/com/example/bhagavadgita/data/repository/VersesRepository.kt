package com.example.bhagavadgita.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bhagavadgita.data.api.ApiHelper
import com.example.bhagavadgita.data.model.Verses
import com.example.bhagavadgita.roomdb.BhagavadGitaDao
import com.example.bhagavadgita.utils.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VersesRepository @Inject constructor(
    private val bhagavadGitaDao: BhagavadGitaDao,
) {
    private val _versesLiveData = MutableLiveData<Verses>()
    val verses: LiveData<Verses>
        get() = _versesLiveData

    suspend fun getParticularVerse(chapterNumber: Int, versesNumber: Int) {
        withContext(Dispatchers.IO) {
            val verses = bhagavadGitaDao.getParticularVerses(versesNumber, chapterNumber)
            _versesLiveData.postValue(verses)
        }

    }
}