package com.example.daboyeo_android.presentation.view.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.ItemReportBinding

class PostsViewHolder(val binding: ItemReportBinding) : RecyclerView.ViewHolder(binding.root) {

    fun initSympathy(sympathy: Boolean) {
        if (!sympathy) Glide.with(binding.root).load(R.drawable.icon_like)
            .into(binding.reportItemLikeButton)
        else Glide.with(binding.root).load(R.drawable.icon_full_like)
            .into(binding.reportItemLikeButton)
    }

    fun setText(content: String, tags: List<String>) {
        binding.reportItemContentTextView.text = content
        binding.reportItemContentTextView.append(" ")
        for(i in 0 until tags.size) {
            binding.reportItemContentTextView.append("#" + tags[i] + " ")
        }
    }


}