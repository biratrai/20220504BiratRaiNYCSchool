package com.nyc.school.highschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nyc.school.R;
import com.nyc.school.data.HighSchools;
import com.nyc.school.data.Result;
import com.nyc.school.satscore.SatScoreActivity;

import java.util.Collections;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HighSchoolActivity extends AppCompatActivity implements HighSchoolAdapter.OnItemClickListener {

    public static final String HIGH_SCHOOL_DBN = "Sat_Score";
    private HighSchoolAdapter highSchoolAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_school);

        initializeViews();

        initializeData();
    }

    private void initializeData() {
        HighSchoolViewModel highSchoolViewModel = new ViewModelProvider(this).get(HighSchoolViewModel.class);

        highSchoolViewModel.getHighSchools().observe(this, this::setHighSchoolData);

        highSchoolViewModel.fetchSchools();
    }

    private void setHighSchoolData(Result<List<HighSchools>> highSchools) {
        if (highSchools instanceof Result.Success) {
            highSchoolAdapter.setData(((Result.Success<List<HighSchools>>) highSchools).data);
        } else if (highSchools instanceof Result.Error || highSchools == null) {
            findViewById(R.id.error_message).setVisibility(View.VISIBLE);
        }
        findViewById(R.id.progress_bar).setVisibility(View.GONE);
    }

    private void initializeViews() {
        RecyclerView recyclerView = findViewById(R.id.high_school_recycler_view);
        LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        highSchoolAdapter = new HighSchoolAdapter(Collections.emptyList(), this);
        recyclerView.setAdapter(highSchoolAdapter);
    }

    @Override
    public void onItemClicked(String highSchoolDbn) {
        Intent intent = new Intent(this, SatScoreActivity.class);
        intent.putExtra(HIGH_SCHOOL_DBN, highSchoolDbn);
        startActivity(intent);
    }
}