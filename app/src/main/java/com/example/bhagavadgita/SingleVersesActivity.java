package com.example.bhagavadgita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.bhagavadgita.Adapters.CommentaryAdapter;
import com.example.bhagavadgita.Adapters.TranslationAdapter;
import com.example.bhagavadgita.Local.SharedPreferencesManager;
import com.example.bhagavadgita.Models.Verses;
import com.example.bhagavadgita.Retrofit.ApiClient;
import com.example.bhagavadgita.Retrofit.ApiService;
import com.example.bhagavadgita.ViewModel.SingleVersesViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleVersesActivity extends AppCompatActivity {

    TextView tvText;
    RecyclerView rvTranslations;
    RecyclerView rvCommentaries;
    TranslationAdapter translationAdapter;
    CommentaryAdapter commentaryAdapter;

    SingleVersesViewModel singleVersesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_verses);

        tvText = findViewById(R.id.tvText);
        rvTranslations = findViewById(R.id.rvTranslations);
        rvCommentaries = findViewById(R.id.rvCommentaries);
        final Intent intent = getIntent();
        final int cNumber = intent.getIntExtra("chapterNumber",0);
        final int vNumber = intent.getIntExtra("versesNumber",0);

        singleVersesViewModel = new ViewModelProvider(this).get(SingleVersesViewModel.class);

        if (SharedPreferencesManager.getVerseByChapterAndVerseNumber(SingleVersesActivity.this,cNumber,vNumber) != null){
            singleVersesViewModel.setVerses(SharedPreferencesManager.getVerseByChapterAndVerseNumber(this,cNumber,vNumber));
        }


        if (singleVersesViewModel.getVerses().getValue() == null) {
            getParticularVerse(cNumber,vNumber);
        }
        else {

            final Verses verses = singleVersesViewModel.getVerses().getValue();

            tvText.setText(verses.getText());
            translationAdapter = new TranslationAdapter(verses.getTranslations(),SingleVersesActivity.this);
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SingleVersesActivity.this,LinearLayoutManager.VERTICAL,false);
            rvTranslations.setLayoutManager(linearLayoutManager);
            rvTranslations.setAdapter(translationAdapter);

            commentaryAdapter = new CommentaryAdapter(verses.getCommentaries(),SingleVersesActivity.this);
            final LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(SingleVersesActivity.this,LinearLayoutManager.VERTICAL,false);
            rvCommentaries.setLayoutManager(linearLayoutManager1);
            rvCommentaries.setAdapter(commentaryAdapter);
        }


    }

    private void getParticularVerse(final int chapterNumber,final int versesNumber){
        LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.show();
        ApiService apiService = ApiClient.getApiService();
        Call<Verses> getParticularVerse = apiService.getParticularVerse(chapterNumber,versesNumber);
        getParticularVerse.enqueue(new Callback<Verses>() {
            @Override
            public void onResponse(Call<Verses> call, Response<Verses> response) {
                loadingDialog.dismiss();
                Log.i("apiCallResponse", "onResponse:versesParticular:code: "+response.code());
                Log.i("apiCallResponse", "onResponse:versesParticular:isSuccessful: "+response.isSuccessful());
                Log.i("apiCallResponse", "onResponse:versesParticular:body: "+response.body());

                singleVersesViewModel.setVerses(response.body());

                tvText.setText(singleVersesViewModel.getVerses().getValue().getText());
                translationAdapter = new TranslationAdapter(singleVersesViewModel.getVerses().getValue().getTranslations(),SingleVersesActivity.this);
                final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SingleVersesActivity.this,LinearLayoutManager.VERTICAL,false);
                rvTranslations.setLayoutManager(linearLayoutManager);
                rvTranslations.setAdapter(translationAdapter);

                commentaryAdapter = new CommentaryAdapter(singleVersesViewModel.getVerses().getValue().getCommentaries(),SingleVersesActivity.this);
                final LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(SingleVersesActivity.this,LinearLayoutManager.VERTICAL,false);
                rvCommentaries.setLayoutManager(linearLayoutManager1);
                rvCommentaries.setAdapter(commentaryAdapter);

            }

            @Override
            public void onFailure(Call<Verses> call, Throwable t) {
                loadingDialog.dismiss();
                Log.e("apiCallError", "onFailure:versesParticular: "+t.getLocalizedMessage());
            }
        });
    }
}