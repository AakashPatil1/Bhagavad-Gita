package com.example.bhagavadgita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.bhagavadgita.Adapters.AllVersesAdapter;
import com.example.bhagavadgita.Local.SharedPreferencesManager;
import com.example.bhagavadgita.Models.Chapters;
import com.example.bhagavadgita.Models.Verses;
import com.example.bhagavadgita.Retrofit.ApiClient;
import com.example.bhagavadgita.Retrofit.ApiService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleChapterActivity extends AppCompatActivity {


    TextView tvNameSingle;
    TextView tvNameTranslatedSingle;
    TextView tvNameMeaning;
    TextView tvSummaryHindi;
    TextView tvSummary;
    TextView tvVersesCount;

    RecyclerView rvVerses;

    AllVersesAdapter allVersesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_chapter);

        tvNameSingle = findViewById(R.id.tvNameSingle);
        tvNameTranslatedSingle = findViewById(R.id.tvNameTranslatedSingle);
        tvNameMeaning = findViewById(R.id.tvNameMeaning);
        tvSummary = findViewById(R.id.tvSummary);
        tvSummaryHindi = findViewById(R.id.tvSummaryHindi);
        tvVersesCount = findViewById(R.id.tvVersesCount);
        rvVerses = findViewById(R.id.rvVerses);



        Intent intent = getIntent();
        final int chapterNumber = intent.getIntExtra("chapterNumber", 0);

        if (SharedPreferencesManager.getChapterByNumber(this, chapterNumber) == null) {

            getParticularChapter(chapterNumber);
        }else{
            Chapters chapters = SharedPreferencesManager.getChapterByNumber(this,chapterNumber);
            tvNameSingle.setText(chapters.getName());
            tvNameTranslatedSingle.setText(chapters.getNameTranslated());
            tvNameMeaning.setText(chapters.getNameMeaning());
            tvSummaryHindi.setText(chapters.getChapterSummaryHindi());
            tvSummary.setText(chapters.getChapterSummary());
            tvVersesCount.setText(String.valueOf(chapters.getVersesCount()));
        }
        if (SharedPreferencesManager.getVerses(SingleChapterActivity.this).size() == 0){
            Log.i("SharedPreferences11", "onCreate:getVerses==0: ");
            getAllVersesFromParticularChapter();
        }else {
            Log.i("SharedPreferences11", "onCreate:getVerses!=0: ");
            allVersesAdapter = new AllVersesAdapter(SharedPreferencesManager.getVerses(SingleChapterActivity.this), SingleChapterActivity.this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SingleChapterActivity.this, RecyclerView.VERTICAL, false);
            rvVerses.setLayoutManager(linearLayoutManager);
            rvVerses.setAdapter(allVersesAdapter);
        }

    }


    private void getParticularChapter(final int chapterNumber) {
        LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.show();
        ApiService apiService = ApiClient.getApiService();

        Call<Chapters> getParticularChapter = apiService.getParticularChapter(chapterNumber);
        getParticularChapter.enqueue(new Callback<Chapters>() {
            @Override
            public void onResponse(Call<Chapters> call, Response<Chapters> response) {
                loadingDialog.dismiss();
                Log.i("apiCallResponse", "onResponse:particular:code: " + response.code());
                Log.i("apiCallResponse", "onResponse:particular:isSuccessful: " + response.isSuccessful());
                Log.i("apiCallResponse", "onResponse:particular:body: " + response.body());

                tvNameSingle.setText(response.body().getName());
                tvNameTranslatedSingle.setText(response.body().getNameTranslated());
                tvNameMeaning.setText(response.body().getNameMeaning());
                tvSummaryHindi.setText(response.body().getChapterSummaryHindi());
                tvSummary.setText(response.body().getChapterSummary());
                tvVersesCount.setText(String.valueOf(response.body().getVersesCount()));



            }

            @Override
            public void onFailure(Call<Chapters> call, Throwable t) {
                loadingDialog.dismiss();
                Log.e("apiCallError", "onFailure:particular: " + t.getLocalizedMessage());
            }
        });

    }

    private void getAllVersesFromParticularChapter() {
        LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.show();
        ApiService apiService = ApiClient.getApiService();
        Call<List<Verses>> getAllVersesFromParticularChapter = apiService.getAllVersesFromParticularChapter(1);
        getAllVersesFromParticularChapter.enqueue(new Callback<List<Verses>>() {
            @Override
            public void onResponse(Call<List<Verses>> call, Response<List<Verses>> response) {
                loadingDialog.dismiss();
                Log.i("apiCallResponse", "onResponse:versesParticularChapter:code: " + response.code());
                Log.i("apiCallResponse", "onResponse:versesParticularChapter:isSuccessful: " + response.isSuccessful());
                Log.i("apiCallResponse", "onResponse:versesParticularChapter:body: " + new Gson().toJson(response.body()));

                SharedPreferencesManager.saveListVerses(SingleChapterActivity.this,response.body());
                Log.i("SharedPreferences11", "onCreate:getVerses:: "+SharedPreferencesManager.getVerses(SingleChapterActivity.this));
                allVersesAdapter = new AllVersesAdapter(response.body(), SingleChapterActivity.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SingleChapterActivity.this, RecyclerView.VERTICAL, false);
                rvVerses.setLayoutManager(linearLayoutManager);
                rvVerses.setAdapter(allVersesAdapter);

            }

            @Override
            public void onFailure(Call<List<Verses>> call, Throwable t) {
                loadingDialog.dismiss();
                Log.e("apiCallError", "onFailure:versesParticularChapter: " + t.getLocalizedMessage());
            }
        });
    }
}