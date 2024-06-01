package com.example.bhagavadgita.ui.verses.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bhagavadgita.ui.verses.adapter.CommentaryAdapter
import com.example.bhagavadgita.ui.verses.adapter.TranslationAdapter
import com.example.bhagavadgita.data.model.Verses
import com.example.bhagavadgita.databinding.ActivitySingleVersesBinding
import com.example.bhagavadgita.ui.verses.viewModel.SingleVersesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleVersesActivity : AppCompatActivity() {
    private lateinit var translationAdapter: TranslationAdapter
    private lateinit var commentaryAdapter: CommentaryAdapter
    private val singleVersesViewModel: SingleVersesViewModel by viewModels()
    private lateinit var activitySingleVersesBinding: ActivitySingleVersesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySingleVersesBinding = ActivitySingleVersesBinding.inflate(
            layoutInflater
        )
        val view: View = activitySingleVersesBinding.root
        setContentView(view)
        val intent = intent
        val cNumber = intent.getIntExtra("chapterNumber", 0)
        val vNumber = intent.getIntExtra("versesNumber", 0)

        singleVersesViewModel.fetchSingleVerses(cNumber, vNumber)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        val linearLayoutManager =
            LinearLayoutManager(this@SingleVersesActivity, LinearLayoutManager.VERTICAL, false)
        translationAdapter = TranslationAdapter(ArrayList(), this@SingleVersesActivity)
        activitySingleVersesBinding.rvTranslations.layoutManager = linearLayoutManager
        activitySingleVersesBinding.rvTranslations.adapter = translationAdapter
        val linearLayoutManage =
            LinearLayoutManager(this@SingleVersesActivity, LinearLayoutManager.VERTICAL, false)
        commentaryAdapter = CommentaryAdapter(ArrayList(), this@SingleVersesActivity)
        activitySingleVersesBinding.rvCommentaries.layoutManager = linearLayoutManage
        activitySingleVersesBinding.rvCommentaries.adapter = commentaryAdapter
    }

    private fun setupObserver() {
        singleVersesViewModel.verses.observe(this, Observer {
            notifyData(it)
        })
    }

    private fun notifyData(it: Verses) {
        activitySingleVersesBinding.tvText.text = it.getText()
        translationAdapter.addData(it.translations)
        translationAdapter.notifyDataSetChanged()
        commentaryAdapter.addData(it.commentaries)
        commentaryAdapter.notifyDataSetChanged()
    }

}