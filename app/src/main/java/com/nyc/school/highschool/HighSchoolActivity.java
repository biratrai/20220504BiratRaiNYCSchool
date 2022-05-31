package com.nyc.school.highschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.nyc.school.R;
import com.nyc.school.data.HighSchools;
import com.nyc.school.data.Result;
import com.nyc.school.databinding.ActivityHighSchoolBinding;
import com.nyc.school.satscore.SatScoreActivity;

import java.util.Collections;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HighSchoolActivity extends AppCompatActivity implements HighSchoolAdapter.OnItemClickListener {

    public static final String HIGH_SCHOOL_DBN = "Sat_Score";
    private HighSchoolAdapter highSchoolAdapter;
    private ActivityHighSchoolBinding activityHighSchoolBinding;
    private HighSchoolViewModel highSchoolViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHighSchoolBinding = ActivityHighSchoolBinding.inflate(getLayoutInflater());
        setContentView(activityHighSchoolBinding.getRoot());

        initializeViews();

        initializeData();
    }

    private void initializeData() {
        highSchoolViewModel = new ViewModelProvider(this).get(HighSchoolViewModel.class);

        highSchoolViewModel.getHighSchools().observe(this, this::setHighSchoolData);

        highSchoolViewModel.fetchSchools();
    }

    private void setHighSchoolData(Result<List<HighSchools>> highSchools) {
        if (highSchools instanceof Result.Success) {
            highSchoolAdapter.setData(((Result.Success<List<HighSchools>>) highSchools).data);
        } else if (highSchools instanceof Result.Error || highSchools == null) {
            activityHighSchoolBinding.errorMessage.setVisibility(View.VISIBLE);
        }
        activityHighSchoolBinding.progressBar.setVisibility(View.GONE);
    }

    private void initializeViews() {
        RecyclerView recyclerView = findViewById(R.id.high_school_recycler_view);
        LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        highSchoolAdapter = new HighSchoolAdapter(Collections.emptyList(), this);
        recyclerView.setAdapter(highSchoolAdapter);

        activityHighSchoolBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String searchString) {
                handleSearch(searchString);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchString) {
                handleSearch(searchString);
                return false;
            }
        });
    }

    private void handleSearch(String searchString) {
        highSchoolAdapter.getFilter().filter(searchString);
    }

    @Override
    public void onItemClicked(String highSchoolDbn) {
        Intent intent = new Intent(this, SatScoreActivity.class);
        intent.putExtra(HIGH_SCHOOL_DBN, highSchoolDbn);
        startActivity(intent);
    }
}