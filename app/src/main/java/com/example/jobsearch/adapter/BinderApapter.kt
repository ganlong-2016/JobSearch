package com.example.jobsearch.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.jobsearch.R

/**
 * @FileName:  BinderApapter
 * @Author:  ganlong
 * @CreateDate:  2022/7/22
 * @Description:
 */
@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    Glide.with(view).load(imageUrl).error(R.mipmap.ic_launcher).into(view)
}
