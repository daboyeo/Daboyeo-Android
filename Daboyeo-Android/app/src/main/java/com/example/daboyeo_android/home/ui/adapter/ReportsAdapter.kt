package com.example.daboyeo_android.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.daboyeo_android.R
import com.example.daboyeo_android.home.model.ReportData

class ReportsAdapter(private val reports: List<ReportData>): RecyclerView.Adapter<ReportsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_report, parent, false)
        return ReportsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReportsViewHolder, position: Int) = holder.bind(reports[position])

    override fun getItemCount(): Int = reports.size

}