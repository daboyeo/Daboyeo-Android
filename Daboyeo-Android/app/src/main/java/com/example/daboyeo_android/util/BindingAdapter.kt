package com.example.daboyeo_android.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


object BindingAdapter {

    @JvmStatic
    @BindingAdapter("imageLoad")
    fun loadImage(view: ImageView, resId: String?) {
        Glide.with(view.context)
            .load(resId)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("circleImg")
    fun loadProfileImage(view: ImageView, resId: String?) {
        Glide.with(view.context)
            .load(resId)
            .circleCrop()
            .into(view)
    }
}