package com.nyc.school.satscore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.nyc.school.R;
import com.nyc.school.data.SatScoreViewModel;

import android.os.Bundle;
import android.util.Log;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SatScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sat_score);

        SatScoreViewModel satScoreViewModel = new ViewModelProvider(this).get(SatScoreViewModel.class);
        satScoreViewModel.getSatScore().observe(this, satResult -> {
            Log.d("TAG", "Bire satscore "+ satResult);
        });
        satScoreViewModel.fetchSatScore("01M292");
    }
}