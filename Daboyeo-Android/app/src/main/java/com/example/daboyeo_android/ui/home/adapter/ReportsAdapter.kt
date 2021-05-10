package com.example.daboyeo_android.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.ItemReportBinding
import com.example.daboyeo_android.entity.home.ReportData

class ReportsAdapter(): RecyclerView.Adapter<ReportsViewHolder>() {
    lateinit var reports : List<ReportData>

    fun setList(list: List<ReportData>) {
        if(::reports.isInitialized) return
        reports = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportsViewHolder {
        val binding = ItemReportBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ReportsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportsViewHolder, position: Int) = holder.bind(reports[position])

    override fun getItemCount(): Int = reports.size

}