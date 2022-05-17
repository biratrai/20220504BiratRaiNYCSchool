package com.nyc.school.network;

import com.nyc.school.data.HighSchools;
import com.nyc.school.data.SatScore;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface SchoolApi {

    @GET("s3k6-pzi2.json")
    Call<List<HighSchools>> getSchool();

    @GET("f9bf-2cp4.json")
    Call<List<SatScore>> getSatScores(@Query("dbn") String schoolId);
}


//https://data.cityofnewyork.us/resource/f9bf-2cp4.json?dbn=31R080
//https://data.cityofnewyork.us/resource/f9bf-2cp4.json
//https://data.cityofnewyork.us/resource/s3k6-pzi2.json