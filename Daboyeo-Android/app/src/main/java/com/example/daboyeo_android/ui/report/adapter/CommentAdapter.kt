package com.example.daboyeo_android.ui.report.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.daboyeo_android.databinding.ItemCommentBinding
import com.example.daboyeo_android.entity.home.CommentData
import com.example.daboyeo_android.ui.home.adapter.OnItemClickListener
import okhttp3.internal.notifyAll

class CommentAdapter(var comments: ArrayList<CommentData>): RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentAdapter.CommentViewHolder, position: Int) {
        val view = holder.itemView
        val item: CommentData = comments[position]

        holder.binding.commentItem = item
        holder.deleteComment(item.comment_id)
    }

    override fun getItemCount(): Int = comments.size

    fun addComment(commentData: CommentData) {
        comments.add(commentData)
        comments.notifyAll()

    }

    inner class CommentViewHolder(val binding: ItemCommentBinding): RecyclerView.ViewHolder(binding.root) {

        fun deleteComment(commentId: Int) {

        }
    }
}