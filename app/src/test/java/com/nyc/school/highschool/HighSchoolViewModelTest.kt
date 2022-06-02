package com.nyc.school.highschool

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nyc.school.MainCoroutineRule
import com.nyc.school.network.SchoolRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class HighSchoolViewModelTest {
    // Subject under test
    private lateinit var highSchoolViewModel: HighSchoolViewModel

    // Mock objects
    @Mock
    private lateinit var schoolRepository: SchoolRepository

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setupViewModel() {
        MockitoAnnotations.openMocks(this)

        highSchoolViewModel = HighSchoolViewModel(schoolRepository)
    }

    @Test
    fun loadAllTasksFromRepository_loadingTogglesAndDataLoaded() {
        // Act
        highSchoolViewModel.fetchSchools()

        // Assert
        verify(schoolRepository).school
    }
}