package com.example.jobsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.jobsearch.R
import com.example.jobsearch.adapter.JobAdapter
import com.example.jobsearch.data.Job
import com.example.jobsearch.databinding.ActivityJobListBinding

class JobListActivity : AppCompatActivity() {

    private val adapter = JobAdapter()
    private lateinit var binding: ActivityJobListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_job_list)
        setContentView(binding.root)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        binding.toolBar.setNavigationOnClickListener {
            onBackPressed()
        }
        val jobs = intent.getParcelableArrayListExtra<Job>("jobs")
        binding.rvJob.adapter = adapter
        adapter.submitList(jobs)
    }
}