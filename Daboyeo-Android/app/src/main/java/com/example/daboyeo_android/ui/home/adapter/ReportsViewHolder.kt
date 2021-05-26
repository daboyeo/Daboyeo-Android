package com.example.daboyeo_android.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.ItemReportBinding
import com.example.daboyeo_android.entity.home.ReportData
import com.example.daboyeo_android.ui.report.ReportViewModel

class ReportsViewHolder(val binding: ItemReportBinding): RecyclerView.ViewHolder(binding.root) {
    private val viewModel = ReportViewModel()
    private lateinit var mListener: OnItemClickListener
    private var reportId: Int? = null

    init{
        itemView.setOnClickListener {
            val pos = adapterPosition
            if(pos != RecyclerView.NO_POSITION) {
                if(mListener != null) {
                    mListener.onItemClick(it, reportId!!.toInt())
                }
            }
        }
    }

    private fun setOnItemClickListener(listener: OnItemClickListener, reportId: Int) {
        mListener = listener
        this.reportId = reportId
    }


    fun clickSympathy(reportId: Int, sympathy: Boolean) {
        if(!sympathy) {
            Glide.with(binding.root).load(R.drawable.icon_full_like).into(binding.reportItemLikeButton)
        } else {
            Glide.with(binding.root).load(R.drawable.icon_like).into(binding.reportItemLikeButton)
        }

        viewModel.clickSympathy(reportId)
    }

    fun clickTag() {

    }

}