package com.nyc.school.network;

import com.nyc.school.data.HighSchools;
import com.nyc.school.data.Result;
import com.nyc.school.data.SatResult;

import java.util.List;

public interface SchoolRepository {
    List<HighSchools> getSchool();

    Result<SatResult> getSatScores(String schoolId);
}
