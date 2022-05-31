package com.nyc.school.network;

import static com.nyc.school.data.Result.*;
import static com.nyc.school.data.Result.Error;

import android.util.Log;

import com.nyc.school.data.HighSchools;
import com.nyc.school.data.Result;
import com.nyc.school.data.SatScore;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;


public class SchoolRepositoryImpl implements SchoolRepository {
    public static final String TAG = SchoolRepositoryImpl.class.getSimpleName();
    private final SchoolService schoolService;

    @Inject
    SchoolRepositoryImpl(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @Override
    public Result<List<HighSchools>> getSchool() {
        Result<List<HighSchools>> result = null;
        try {
            List<HighSchools> schools = schoolService.getSchool().execute().body();
            Log.d(TAG, "getSchool() "+ schools);
            if (!(schools != null && schools.isEmpty())) {
                result = new Success<>(schools);
            }
            return result;
        } catch (IOException e) {
            Log.e(TAG, "Exception while fetching high school list "+e);
            return new Error<>(e);
        }
    }

    @Override
    public Result<List<HighSchools>> searchSchool(String searchString) {
        Result<List<HighSchools>> result = null;
        try {
            List<HighSchools> schools = schoolService.searchSchool(searchString).execute().body();
            if (!(schools != null && schools.isEmpty())) {
                result = new Success<>(schools);
            }
            return result;
        } catch (IOException e) {
            Log.e(TAG, "Exception while fetching high school list "+e);
            return new Error<>(e);
        }
    }

    @Override
    public Result getSatScores(String schoolId) {
        Result<SatScore> result = null;
        try {
            Call<List<SatScore>> call = schoolService.getSatScores(schoolId);
            List<SatScore> results = call.execute().body();
            Log.d(TAG, "The satResult "+ results);
            if (!(results != null && results.isEmpty())) {
                result = new Success<>(results.get(0));
            }
        } catch (IOException e) {
            Log.e(TAG, "Exception occurred while fetching sat score ", e);
            result = new Error<>(e);
        }
        return result;
    }

}
