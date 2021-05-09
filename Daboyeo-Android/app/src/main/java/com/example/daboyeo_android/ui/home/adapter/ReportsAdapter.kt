package com.example.daboyeo_android.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.daboyeo_android.R
import com.example.daboyeo_android.entity.home.ReportData

class ReportsAdapter(): RecyclerView.Adapter<ReportsViewHolder>() {
    lateinit var reports : List<ReportData>

    fun setList(list: List<ReportData>) {
        if(::reports.isInitialized) return
        reports = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_report, parent, false)
        return ReportsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReportsViewHolder, position: Int) = holder.bind(reports[position])

    override fun getItemCount(): Int = reports.size

}