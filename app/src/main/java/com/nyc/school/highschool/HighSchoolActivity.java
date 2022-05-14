package com.nyc.school.highschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.nyc.school.R;

import java.util.Collections;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HighSchoolActivity extends AppCompatActivity {

    private HighSchoolViewModel highSchoolViewModel;
    private HighSchoolAdapter highSchoolAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_school);

        highSchoolViewModel = new ViewModelProvider(this).get(HighSchoolViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.high_school_recycler_view);
        LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        highSchoolAdapter = new HighSchoolAdapter(Collections.emptyList());
        recyclerView.setAdapter(highSchoolAdapter);

        highSchoolViewModel.getHighSchools().observe(this, highSchools -> {
            Log.d("TAG", "Bire highSchools "+ highSchools.size());
            highSchoolAdapter.setData(highSchools);
        });

        highSchoolViewModel.fetchSchools();
    }
}