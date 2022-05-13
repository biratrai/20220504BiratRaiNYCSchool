package com.nyc.school.data

import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import com.nyc.school.network.SchoolRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HighSchoolViewModel @Inject constructor(private val schoolRepository: SchoolRepository): ViewModel() {
    private val _highSchools = MutableLiveData<List<HighSchools>>()
    var highSchools: LiveData<List<HighSchools>> = _highSchools

    fun fetchSchools() {
        viewModelScope.launch(Dispatchers.IO) {
            val schools = schoolRepository.school
            _highSchools.postValue(schools)
        }
    }
}

