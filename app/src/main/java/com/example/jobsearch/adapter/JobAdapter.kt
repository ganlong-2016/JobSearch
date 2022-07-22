package com.example.jobsearch.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jobsearch.JobDetailActivity
import com.example.jobsearch.data.Job
import com.example.jobsearch.databinding.ItemJobListBinding

/**
 * @FileName:  JobAdapter
 * @Author:  ganlong
 * @CreateDate:  2022/7/22
 * @Description:
 */
class JobAdapter : ListAdapter<Job, JobAdapter.JobViewHolder>(JobDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        return JobViewHolder(
            ItemJobListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = getItem(position)
        job?.let {
            holder.bind(it)
        }
    }

    class JobViewHolder(
        private val binding: ItemJobListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.tvJobTitle.setOnClickListener {
                it.context.startActivity(
                    Intent(it.context, JobDetailActivity::class.java).putExtra(
                        "job",
                        binding.job
                    )
                )
            }
        }

        fun bind(item: Job) {
            binding.apply {
                job = item
                executePendingBindings()
            }
        }
    }
}

private class JobDiffCallback : DiffUtil.ItemCallback<Job>() {

    override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean {
        return oldItem == newItem
    }
}