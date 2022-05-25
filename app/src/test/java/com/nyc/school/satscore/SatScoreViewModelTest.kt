package com.nyc.school.satscore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nyc.school.MainCoroutineRule
import com.nyc.school.network.SchoolRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class SatScoreViewModelTest {
    // Subject under test
    private lateinit var satScoreViewModel: SatScoreViewModel

    // Mock initialize
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

        satScoreViewModel = SatScoreViewModel(schoolRepository)
    }

    @Test
    fun loadAllTasksFromRepository_loadingTogglesAndDataLoaded() {
        satScoreViewModel.fetchSatScore("schoolId")
        Mockito.verify(schoolRepository).getSatScores("schoolId")
    }
}