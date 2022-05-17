package com.nyc.school.satscore

import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import com.nyc.school.network.SchoolRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nyc.school.data.Result
import com.nyc.school.data.SatScore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatScoreViewModel @Inject constructor(private val schoolRepository: SchoolRepository): ViewModel() {
    private val _satScore = MutableLiveData<Result<SatScore>>()
    var satScore: LiveData<Result<SatScore>> = _satScore

    fun fetchSatScore(schoolId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val satScore = schoolRepository.getSatScores(schoolId)
            _satScore.postValue(satScore)
        }
    }

}

