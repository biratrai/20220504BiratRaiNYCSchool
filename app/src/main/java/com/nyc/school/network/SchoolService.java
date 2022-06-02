package com.nyc.school.network;

import com.nyc.school.data.HighSchools;
import com.nyc.school.data.SatScore;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Retrofit;

@Singleton
class SchoolService implements SchoolApi {
    private final SchoolApi schoolApi;

    @Inject
    SchoolService(SchoolApi schoolApi) {
        this.schoolApi = schoolApi;
    }

    @Override
    public Call<List<HighSchools>> getSchool() {
        return schoolApi.getSchool();
    }

    @Override
    public Call<List<HighSchools>> searchSchool(String searchString) {
        return schoolApi.searchSchool(searchString);
    }

    @Override
    public Call<List<SatScore>> getSatScores(String schoolId) {
        return schoolApi.getSatScores(schoolId);
    }
}
