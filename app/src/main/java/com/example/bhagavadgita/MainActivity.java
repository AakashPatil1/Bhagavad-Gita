package com.example.bhagavadgita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.bhagavadgita.Adapters.AllChaptersAdapter;
import com.example.bhagavadgita.Local.SharedPreferencesManager;
import com.example.bhagavadgita.Models.Chapters;
import com.example.bhagavadgita.Retrofit.ApiClient;
import com.example.bhagavadgita.Retrofit.ApiService;
import com.example.bhagavadgita.ViewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {


    private RecyclerView rvChapters;
    private AllChaptersAdapter allChaptersAdapter;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvChapters = findViewById(R.id.rvChapters);

        /*if (SharedPreferencesManager.getChapters(this).size() == 0) {
            Log.i("SharedPreferences", "onCreate:getChapters==0: ");
            getAllChapters();
        } else {
            Log.i("SharedPreferences", "onCreate:getChapters!=0: ");
            allChaptersAdapter = new AllChaptersAdapter(SharedPreferencesManager.getChapters(this), MainActivity.this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
            rvChapters.setLayoutManager(linearLayoutManager);
            rvChapters.setAdapter(allChaptersAdapter);
        }*/

        // Initialize your ViewModel
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class); // Use ViewModelProvider

        rvChapters = findViewById(R.id.rvChapters);

        if(SharedPreferencesManager.getChapters(this).size()!=0){
            mainViewModel.setChapters(SharedPreferencesManager.getChapters(this));
        }

        if (mainViewModel.getChaptersLiveData().getValue() == null) {
            Log.i("ViewModel", "onCreate: chaptersLiveData is empty");
            getAllChapters();
        } else {
            Log.i("ViewModel", "onCreate: chaptersLiveData is not empty");
            allChaptersAdapter = new AllChaptersAdapter(mainViewModel.getChaptersLiveData().getValue(), MainActivity.this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
            rvChapters.setLayoutManager(linearLayoutManager);
            rvChapters.setAdapter(allChaptersAdapter);
        }


    }

    private void getAllChapters() {
        LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.show();
        ApiService apiService = ApiClient.getApiService();

        Call<List<Chapters>> getAllChapters = apiService.GetAllChapters();
        getAllChapters.enqueue(new Callback<List<Chapters>>() {
            @Override
            public void onResponse(Call<List<Chapters>> call, Response<List<Chapters>> response) {
                loadingDialog.dismiss();
                Log.i("apiCallResponse", "onResponse:code: " + response.code());
                Log.i("apiCallResponse", "onResponse:isSuccessful: " + response.isSuccessful());
                Log.i("apiCallResponse", "onResponse:body: " + response.body());

                if (response.isSuccessful()) {
                    SharedPreferencesManager.saveListChapter(MainActivity.this, response.body());
                    mainViewModel.setChapters(response.body());
                    allChaptersAdapter = new AllChaptersAdapter(mainViewModel.getChaptersLiveData().getValue(), MainActivity.this);
                    final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
                    rvChapters.setLayoutManager(linearLayoutManager);
                    rvChapters.setAdapter(allChaptersAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<Chapters>> call, Throwable t) {
                loadingDialog.dismiss();
                Log.e("apiCallError", "onFailure:getAllChapters: " + t.getLocalizedMessage());
            }
        });
    }


}