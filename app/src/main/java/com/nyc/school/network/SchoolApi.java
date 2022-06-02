package com.nyc.school.network;

import com.nyc.school.data.HighSchools;
import com.nyc.school.data.SatScore;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SchoolApi {

    @GET("s3k6-pzi2.json")
    Call<List<HighSchools>> getSchool();

    @GET("s3k6-pzi2.json")
    Call<List<HighSchools>> searchSchool(@Query("source") String searchString);

    @GET("f9bf-2cp4.json")
    Call<List<SatScore>> getSatScores(@Query("dbn") String schoolId);
}