package com.example.daboyeo_android.ui.report.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.daboyeo_android.databinding.ItemCommentBinding
import com.example.daboyeo_android.entity.home.CommentData

class CommentViewHolder(private val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(commentData: CommentData) {
        binding.commentItemContentTextView.text = commentData.content
        binding.commentItemNameTextView.text=commentData.name
        Glide.with(binding.root).load(commentData.profile_uri).into(binding.commentItemProfileImageView)
    }

}