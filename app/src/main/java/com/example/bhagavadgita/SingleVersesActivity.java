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

import com.example.bhagavadgita.Adapters.CommentaryAdapter;
import com.example.bhagavadgita.Adapters.TranslationAdapter;
import com.example.bhagavadgita.Local.SharedPreferencesManager;
import com.example.bhagavadgita.Models.Verses;
import com.example.bhagavadgita.Retrofit.ApiClient;
import com.example.bhagavadgita.Retrofit.ApiService;
import com.example.bhagavadgita.ViewModel.SingleVersesViewModel;
import com.example.bhagavadgita.databinding.ActivitySingleVersesBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleVersesActivity extends AppCompatActivity {


    TranslationAdapter translationAdapter;
    CommentaryAdapter commentaryAdapter;

    private SingleVersesViewModel singleVersesViewModel;
    private ActivitySingleVersesBinding activitySingleVersesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySingleVersesBinding = ActivitySingleVersesBinding.inflate(getLayoutInflater());
        View view = activitySingleVersesBinding.getRoot();
        setContentView(view);

        final Intent intent = getIntent();
        final int cNumber = intent.getIntExtra("chapterNumber", 0);
        final int vNumber = intent.getIntExtra("versesNumber", 0);

        singleVersesViewModel = new ViewModelProvider(this).get(SingleVersesViewModel.class);

        if (SharedPreferencesManager.getVerseByChapterAndVerseNumber(SingleVersesActivity.this, cNumber, vNumber) != null) {
            singleVersesViewModel.setVerses(SharedPreferencesManager.getVerseByChapterAndVerseNumber(this, cNumber, vNumber));
        }


        if (singleVersesViewModel.getVerses().getValue() == null) {
            getParticularVerse(cNumber, vNumber);
        } else {

            final Verses verses = singleVersesViewModel.getVerses().getValue();

            updateUIFromVerseResponse(verses);
        }


    }

    private void getParticularVerse(final int chapterNumber, final int versesNumber) {
        LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.show();
        ApiService apiService = ApiClient.getApiService();
        Call<Verses> getParticularVerse = apiService.getParticularVerse(chapterNumber, versesNumber);
        getParticularVerse.enqueue(new Callback<Verses>() {
            @Override
            public void onResponse(Call<Verses> call, Response<Verses> response) {
                loadingDialog.dismiss();
                Log.i("apiCallResponse", "onResponse:versesParticular:code: " + response.code());
                Log.i("apiCallResponse", "onResponse:versesParticular:isSuccessful: " + response.isSuccessful());
                Log.i("apiCallResponse", "onResponse:versesParticular:body: " + response.body());

                if (response.body() != null) {

                    singleVersesViewModel.setVerses(response.body());
                    updateUIFromVerseResponse(singleVersesViewModel.getVerses().getValue());
                }


            }

            @Override
            public void onFailure(Call<Verses> call, Throwable t) {
                loadingDialog.dismiss();
                Log.e("apiCallError", "onFailure:versesParticular: " + t.getLocalizedMessage());
            }
        });
    }


    private void updateUIFromVerseResponse(Verses verses) {
        activitySingleVersesBinding.tvText.setText(verses.getText());
        translationAdapter = new TranslationAdapter(verses.getTranslations(), SingleVersesActivity.this);
        setupRecyclerView(activitySingleVersesBinding.rvTranslations, translationAdapter);

        commentaryAdapter = new CommentaryAdapter(verses.getCommentaries(), SingleVersesActivity.this);
        setupRecyclerView(activitySingleVersesBinding.rvCommentaries, commentaryAdapter);
    }


    private void setupRecyclerView(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SingleVersesActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

}