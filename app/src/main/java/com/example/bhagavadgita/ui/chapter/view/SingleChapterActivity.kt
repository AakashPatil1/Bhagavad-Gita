package com.example.bhagavadgita.ui.chapter.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bhagavadgita.ui.chapter.adapter.AllVersesAdapter
import com.example.bhagavadgita.data.model.Chapters
import com.example.bhagavadgita.data.model.Verses
import com.example.bhagavadgita.databinding.ActivitySingleChapterBinding
import com.example.bhagavadgita.ui.chapter.viewModel.SingleChapterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleChapterActivity : AppCompatActivity() {
    private lateinit var allVersesAdapter: AllVersesAdapter
    private val singleChapterViewModel: SingleChapterViewModel by viewModels()
    private lateinit var activitySingleChapterBinding: ActivitySingleChapterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySingleChapterBinding = ActivitySingleChapterBinding.inflate(
            layoutInflater
        )
        val view: View = activitySingleChapterBinding.root
        setContentView(view)

        val intent = intent
        var chapterNumber = intent.getIntExtra("chapterNumber", 0)
        singleChapterViewModel.fetchData(chapterNumber)
        singleChapterViewModel.fetchVersesData(chapterNumber)

        setupUI()
        setupObserver()
    }
    private fun setupUI() {
        allVersesAdapter = AllVersesAdapter(ArrayList(), this@SingleChapterActivity)
        val linearLayoutManager =
            LinearLayoutManager(this@SingleChapterActivity, RecyclerView.VERTICAL, false)
        activitySingleChapterBinding.rvVerses.layoutManager = linearLayoutManager
        activitySingleChapterBinding.rvVerses.adapter = allVersesAdapter
    }

    private fun setupObserver() {
        singleChapterViewModel.chaptersSingleLiveData.observe(this, Observer {
            updateUIFromChapterResponse(it)
        })
        singleChapterViewModel.versesLiveData.observe(this, Observer {
            notifyData(it)
        })
    }

    private fun notifyData(list: List<Verses>) {
        allVersesAdapter.addData(list)
        allVersesAdapter.notifyDataSetChanged()
    }

    private fun updateUIFromChapterResponse(chapters: Chapters?) {
        activitySingleChapterBinding.tvNameSingle.text = chapters!!.name
        activitySingleChapterBinding.tvNameTranslatedSingle.text = chapters.nameTranslated
        activitySingleChapterBinding.tvNameMeaning.text = chapters.nameMeaning
        activitySingleChapterBinding.tvSummaryHindi.text = chapters.chapterSummaryHindi
        activitySingleChapterBinding.tvSummary.text = chapters.chapterSummary
        activitySingleChapterBinding.tvVersesCount.text = chapters.versesCount.toString()
    }

}