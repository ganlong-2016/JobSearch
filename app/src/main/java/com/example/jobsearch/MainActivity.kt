package com.example.jobsearch

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.jobsearch.databinding.ActivityMainBinding
import com.example.jobsearch.viewmodels.MainViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private var job: Job? = null

    private val sharedPref by lazy {
        getPreferences(Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setContentView(binding.root)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        binding.etSearchContent.addTextChangedListener {
            binding.btnSearch.isEnabled = it.toString().isNotEmpty()
        }
        binding.tvSearchHistory.text = getHistory()
        binding.btnSearch.setOnClickListener {

            job?.cancel()
            job = lifecycleScope.launch {
                binding.progressBar.show()
                try {
                    val jobs = viewModel.searchJobs(binding.etSearchContent.text.toString())
                    saveSearchHistory(binding.etSearchContent.text.toString())
                    binding.tvSearchHistory.text = getHistory()
                    startActivity(
                        Intent(
                            this@MainActivity,
                            JobListActivity::class.java
                        ).putParcelableArrayListExtra("jobs", jobs)
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    binding.progressBar.hide()
                }
            }
        }
    }
    private fun getHistory()  = sharedPref.getString("search_history", "")

    private fun saveSearchHistory(queryString: String) {
        val history = sharedPref.getString("search_history", "")
        val gson = Gson()
        var list = ArrayList<String>()
        if (history.isNullOrEmpty().not()){
            list = gson.fromJson<ArrayList<String>>(history, object : TypeToken<ArrayList<String>>() {}.type)
        }
        with(sharedPref.edit()) {
            if (list.size >=5){
                list.removeAt(0)
            }
            list.add(queryString)
            putString("search_history", gson.toJson(list))
            apply()
        }
    }
}