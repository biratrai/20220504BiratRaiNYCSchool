package com.nyc.school.satscore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.nyc.school.R;
import com.nyc.school.data.Result;
import com.nyc.school.data.SatScore;
import com.nyc.school.highschool.HighSchoolActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SatScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sat_score);

        initializeData();
    }

    private void initializeData() {
        SatScoreViewModel satScoreViewModel = new ViewModelProvider(this).get(SatScoreViewModel.class);
        satScoreViewModel.getSatScore().observe(this, satResult -> {
            Log.d("TAG", "Bire satscore " + satResult);
            findViewById(R.id.progress_bar).setVisibility(View.GONE);
            if (satResult instanceof Result.Success) {
                setData((Result.Success<SatScore>) satResult);
            } else if (satResult instanceof Result.Error || satResult == null) {
                findViewById(R.id.error_message).setVisibility(View.VISIBLE);
            }
        });

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String highSchoolDbn = intent.getStringExtra(HighSchoolActivity.HIGH_SCHOOL_DBN);

        // Fetch sat score data
        satScoreViewModel.fetchSatScore(highSchoolDbn);
    }

    private void setData(Result.Success<SatScore> satResult) {
        TextView title = findViewById(R.id.title);
        title.setText("Average Sat score of " + satResult.data.getSchoolName());
        TextView mathScore = findViewById(R.id.math_score);
        mathScore.setText("Math average score: " + satResult.data.getSatMathAvgScore());
        mathScore.setVisibility(View.VISIBLE);
        TextView writingScore = findViewById(R.id.writing_score);
        writingScore.setText("Writing average score: " + satResult.data.getSatWritingAvgScore());
        writingScore.setVisibility(View.VISIBLE);
        TextView readingScore = findViewById(R.id.reading_score);
        readingScore.setText("Reading average score: " + satResult.data.getSatCriticalReadingAvgScore());
        readingScore.setVisibility(View.VISIBLE);
    }
}