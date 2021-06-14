package com.example.daboyeo_android.presentation.view.post.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.ItemCommentBinding
import com.example.daboyeo_android.data.model.home.CommentData
import okhttp3.internal.notifyAll

class CommentAdapter(): RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    private var comments = ArrayList<CommentData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        CommentViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_comment,
                parent,
                false
            )
        )
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val item: CommentData = comments[position]
        holder.binding.commentItem = item
    }

    override fun getItemCount(): Int = comments.size

    fun addComment(commentData: CommentData) {
        comments.add(commentData)
        comments.notifyAll()
    }

    fun setList(comments: ArrayList<CommentData>) {
        this.comments = comments
    }

    inner class CommentViewHolder(val binding: ItemCommentBinding): RecyclerView.ViewHolder(binding.root) {

    }
}