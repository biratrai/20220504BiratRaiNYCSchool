package com.nyc.school.network;

import com.nyc.school.data.HighSchools;
import com.nyc.school.data.SatResult;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Retrofit;

@Singleton
class SchoolService implements SchoolApi {
    private final SchoolApi schoolApi;

    @Inject
    SchoolService(Retrofit retrofit) {
        this.schoolApi = retrofit.create(SchoolApi.class);
    }

    @Override
    public Call<List<HighSchools>> getSchool() {
        return schoolApi.getSchool();
    }

    @Override
    public Call<List<SatResult>> getSatScores(String schoolId) {
        return schoolApi.getSatScores(schoolId);
    }
}
