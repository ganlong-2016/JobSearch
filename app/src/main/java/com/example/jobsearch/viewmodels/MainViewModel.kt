package com.example.jobsearch.viewmodels

import androidx.lifecycle.ViewModel
import com.example.jobsearch.api.ApiService
import com.example.jobsearch.data.Job
import com.example.jobsearch.data.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @FileName:  MainViewModel
 * @Author:  ganlong
 * @CreateDate:  2022/7/21
 * @Description:
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: JobRepository
) : ViewModel(){

    suspend fun searchJobs(queryString: String) : ArrayList<Job>{
        return repository.searchJobs(queryString)
    }
}