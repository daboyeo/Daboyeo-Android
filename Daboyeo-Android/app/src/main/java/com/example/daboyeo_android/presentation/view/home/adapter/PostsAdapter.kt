package com.example.daboyeo_android.presentation.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.daboyeo_android.R
import com.example.daboyeo_android.data.model.home.PostData

class PostsAdapter : RecyclerView.Adapter<PostsViewHolder>() {
    private lateinit var posts: List<PostData>
    private lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder =
            PostsViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.item_report,
                            parent,
                            false
                    )
            )

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val item: PostData = posts[position]

        holder.binding.reportItem = posts[position]
        holder.itemView.apply {
            holder.initSympathy(item.is_sympathy)
            holder.setText(item.content, item.tags)
        }

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(it, item.report_id)
        }

    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun setList(posts: List<PostData>) {
        this.posts = posts
    }

    override fun getItemCount() = posts.size

}