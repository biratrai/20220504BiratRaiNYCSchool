package com.nyc.school.network;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nyc.school.data.HighSchools;
import com.nyc.school.data.Result;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class SchoolRepositoryImplTest {

    @Mock
    SchoolService schoolService;
    @Mock
    Call<List<HighSchools>> call;
    @Mock
    HighSchools highSchools;

    private SchoolRepository schoolRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(schoolService.getSchool()).thenReturn(call);
        schoolRepository = new SchoolRepositoryImpl(schoolService);
    }

    @Test
    public void testGetSchoolFetchesSchoolGivesCorrectData() throws IOException {
        Response<List<HighSchools>> response = Response.success(Collections.singletonList(highSchools));
        when(call.execute()).thenReturn(response);

        Result<List<HighSchools>> result = schoolRepository.getSchool();

        verify(schoolService).getSchool();
        Assert.assertTrue(result instanceof Result.Success);
    }

    @Test
    public void testGetSchoolFetchesSchoolThrowsException() throws IOException {
        when(call.execute()).thenThrow(IOException.class);

        Result<List<HighSchools>> result = schoolRepository.getSchool();

        verify(schoolService).getSchool();
        Assert.assertTrue(result instanceof Result.Error);
        Assert.assertTrue(((Result.Error<List<HighSchools>>) result).exception instanceof IOException);
    }
}