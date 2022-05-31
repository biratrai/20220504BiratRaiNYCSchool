package com.nyc.school.network;

import com.nyc.school.data.HighSchools;
import com.nyc.school.data.Result;
import com.nyc.school.data.SatScore;

import java.util.List;

public interface SchoolRepository {
    Result<List<HighSchools>> getSchool();

    Result<List<HighSchools>> searchSchool(String searchString);

    Result<SatScore> getSatScores(String schoolId);
}
