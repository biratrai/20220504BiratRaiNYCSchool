package com.nyc.school.data

import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import com.nyc.school.network.SchoolRepository
import androidx.lifecycle.MutableLiveData
import com.nyc.school.data.HighSchools
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nyc.school.data.SatResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatScoreViewModel @Inject constructor(private val schoolRepository: SchoolRepository): ViewModel() {
    private val _satScore = MutableLiveData<SatResult>()
    var satScore: LiveData<SatResult> = _satScore

    fun fetchSatScore(schoolId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val satScore = schoolRepository.getSatScores(schoolId)
            _satScore.postValue(satScore)
        }
//        schoolRepository.getSatScores(schoolId)
    }

}

