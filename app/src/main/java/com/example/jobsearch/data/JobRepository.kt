package com.example.jobsearch.data

import com.example.jobsearch.api.ApiService
import com.google.gson.Gson
import org.json.JSONObject
import javax.inject.Inject

/**
 * @FileName:  JobRepository
 * @Author:  ganlong
 * @CreateDate:  2022/7/21
 * @Description:
 */
class JobRepository @Inject constructor(
    private val service: ApiService
){
    suspend fun searchJobs(queryString: String) : ArrayList<Job>{
        val paramObject = JSONObject()
        paramObject.put("query", "query{jobs(input:{type:\"$queryString\"}) {title,company{name,logoUrl},description}}")

        val result =  service.searchJobs(paramObject.toString())
        val data = Gson().fromJson(result.body().toString(),JobSearchResponse::class.java)
        return data.data.jobs
    }
}