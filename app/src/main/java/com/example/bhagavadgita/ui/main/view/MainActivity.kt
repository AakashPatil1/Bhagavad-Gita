package com.example.bhagavadgita.ui.main.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bhagavadgita.ui.main.adapter.AllChaptersAdapter
import com.example.bhagavadgita.data.model.Chapters
import com.example.bhagavadgita.databinding.ActivityMainBinding
import com.example.bhagavadgita.ui.main.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: AllChaptersAdapter

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = activityMainBinding.root
        setContentView(view)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        adapter = AllChaptersAdapter(arrayListOf(), this@MainActivity)
        val linearLayoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        activityMainBinding.rvChapters.layoutManager = linearLayoutManager
        activityMainBinding.rvChapters.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.chapters.observe(this, Observer {
            it.let {
                notifiyList(it.body()!!)
            }
        })
    }


    private fun notifiyList(chapters: List<Chapters>) {
        adapter.addData(chapters)
        adapter.notifyDataSetChanged()
    }
}