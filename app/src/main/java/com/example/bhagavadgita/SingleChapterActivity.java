package com.example.bhagavadgita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.bhagavadgita.Adapters.AllVersesAdapter;
import com.example.bhagavadgita.Local.SharedPreferencesManager;
import com.example.bhagavadgita.Models.Chapters;
import com.example.bhagavadgita.Models.Verses;
import com.example.bhagavadgita.Retrofit.ApiClient;
import com.example.bhagavadgita.Retrofit.ApiService;
import com.example.bhagavadgita.ViewModel.SingleChapterViewModel;
import com.example.bhagavadgita.databinding.ActivitySingleChapterBinding;
import com.example.bhagavadgita.databinding.ActivitySingleVersesBinding;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleChapterActivity extends AppCompatActivity {


    AllVersesAdapter allVersesAdapter;

    private SingleChapterViewModel singleChapterViewModel;

    private ActivitySingleChapterBinding activitySingleChapterBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySingleChapterBinding = ActivitySingleChapterBinding.inflate(getLayoutInflater());
        View view = activitySingleChapterBinding.getRoot();
        setContentView(view);


        singleChapterViewModel = new ViewModelProvider(this).get(SingleChapterViewModel.class);

        final Intent intent = getIntent();
        final int chapterNumber = intent.getIntExtra("chapterNumber", 0);

        if (SharedPreferencesManager.getChapterByNumber(this, chapterNumber) != null) {
            singleChapterViewModel.setSingleChapter(SharedPreferencesManager.getChapterByNumber(this, chapterNumber));
        }
        if (SharedPreferencesManager.getVerses(SingleChapterActivity.this).size() != 0) {
            singleChapterViewModel.setVersesLiveData(SharedPreferencesManager.getVerses(this));
        }


        if (singleChapterViewModel.getChaptersSingleLiveData().getValue() == null) {
            getParticularChapter(chapterNumber);
        } else {
            Chapters chapters = singleChapterViewModel.getChaptersSingleLiveData().getValue();
            if (chapters != null) {
                updateUIFromChapterResponse(singleChapterViewModel.getChaptersSingleLiveData().getValue());
            }
        }

        if (singleChapterViewModel.getVersesLiveData().getValue() == null) {
            Log.i("SharedPreferences11", "onCreate:getVerses==0: ");
            getAllVersesFromParticularChapter();
        } else {
            Log.i("SharedPreferences11", "onCreate:getVerses!=0: ");
           setupVersesRecyclerView(singleChapterViewModel.getVersesLiveData().getValue());
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

                if (response.body() != null) {

                    singleChapterViewModel.setSingleChapter(response.body());
                    updateUIFromChapterResponse(singleChapterViewModel.getChaptersSingleLiveData().getValue());
                }


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

                if (response.body() != null) {

                    SharedPreferencesManager.saveListVerses(SingleChapterActivity.this, response.body());
                    singleChapterViewModel.setVersesLiveData(response.body());
                    Log.i("SharedPreferences11", "onCreate:getVerses:: " + SharedPreferencesManager.getVerses(SingleChapterActivity.this));
                    setupVersesRecyclerView(singleChapterViewModel.getVersesLiveData().getValue());
                }

            }

            @Override
            public void onFailure(Call<List<Verses>> call, Throwable t) {
                loadingDialog.dismiss();
                Log.e("apiCallError", "onFailure:versesParticularChapter: " + t.getLocalizedMessage());
            }
        });
    }


    private void updateUIFromChapterResponse(Chapters chapters) {
        activitySingleChapterBinding.tvNameSingle.setText(chapters.getName());
        activitySingleChapterBinding.tvNameTranslatedSingle.setText(chapters.getNameTranslated());
        activitySingleChapterBinding.tvNameMeaning.setText(chapters.getNameMeaning());
        activitySingleChapterBinding.tvSummaryHindi.setText(chapters.getChapterSummaryHindi());
        activitySingleChapterBinding.tvSummary.setText(chapters.getChapterSummary());
        activitySingleChapterBinding.tvVersesCount.setText(String.valueOf(chapters.getVersesCount()));
    }

    private void setupVersesRecyclerView(List<Verses> versesList) {
        allVersesAdapter = new AllVersesAdapter(versesList, SingleChapterActivity.this);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SingleChapterActivity.this, RecyclerView.VERTICAL, false);
        activitySingleChapterBinding.rvVerses.setLayoutManager(linearLayoutManager);
        activitySingleChapterBinding.rvVerses.setAdapter(allVersesAdapter);
    }
}