package com.nyc.school.highschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.nyc.school.R;
import com.nyc.school.data.HighSchoolViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HighSchoolActivity extends AppCompatActivity {

    private HighSchoolViewModel highSchoolViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_school);

        highSchoolViewModel = new ViewModelProvider(this).get(HighSchoolViewModel.class);

        highSchoolViewModel.getHighSchools().observe(this, highSchools -> {
            Log.d("TAG", "Bire highSchools "+ highSchools);
        });

        highSchoolViewModel.fetchSchools();
    }
}