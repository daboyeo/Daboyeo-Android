package com.example.daboyeo_android.ui.report.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.daboyeo_android.databinding.ItemCommentBinding
import com.example.daboyeo_android.entity.home.CommentData
import okhttp3.internal.notifyAll

class CommentAdapter(): RecyclerView.Adapter<CommentViewHolder>() {
    lateinit var comments : ArrayList<CommentData>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) = holder.bind(comments[position])

    override fun getItemCount(): Int = comments.size

    fun setList(list: ArrayList<CommentData>) {
        if(::comments.isLateinit) return
        comments = list
    }

    fun addComment(commentData: CommentData) {
        comments.add(commentData)
        commentData.notifyAll()
    }
}