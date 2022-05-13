package com.nyc.school.network;

import android.util.Log;

import com.nyc.school.data.HighSchools;
import com.nyc.school.data.SatResult;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;


public class SchoolRepositoryImpl implements SchoolRepository {
    private final SchoolService schoolService;

    @Inject
    SchoolRepositoryImpl(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @Override
    public List<HighSchools> getSchool() {
        try {
            List<HighSchools> schools = schoolService.getSchool().execute().body();
            Log.d("TAG", "Bire getSchool "+ schools);
            return schools;
        } catch (IOException e) {
            Log.e("TAG", "Exception while fetching high school list "+e);
        }
        return Collections.emptyList();
    }

    @Override
    public SatResult getSatScores(String schoolId) {
        SatResult result = null;
        try {
            Call<List<SatResult>> call = schoolService.getSatScores(schoolId);
            List<SatResult> results = call.execute().body();
            Log.d("TAG", "Bire satResult "+ results);
            if (!(results != null && results.isEmpty())) {
                result = results.get(0);
            }
        } catch (IOException e) {
            Log.e("TAG", "Exception occurred while fetching sat score ", e);
        }
        return result;
    }

}
