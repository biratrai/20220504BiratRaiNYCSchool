package com.nyc.school.network;

import static org.mockito.Mockito.verify;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class SchoolServiceTest extends TestCase {
    // Mock objects
    @Mock
    SchoolApi schoolApi;

    // Subject under test
    private SchoolService schoolService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        schoolService = new SchoolService(schoolApi);
    }

    @Test
    public void testGetSchool() {
        // Act
        schoolService.getSchool();

        // Assert
        verify(schoolApi).getSchool();
    }

    public void testSearchSchool() {
        // Act
        schoolService.searchSchool("cli");

        // Assert
        verify(schoolApi).searchSchool("cli");
    }

    public void testGetSatScores() {
        // Act
        schoolService.getSatScores("schoolId");

        // Assert
        verify(schoolApi).getSatScores("schoolId");
    }
}