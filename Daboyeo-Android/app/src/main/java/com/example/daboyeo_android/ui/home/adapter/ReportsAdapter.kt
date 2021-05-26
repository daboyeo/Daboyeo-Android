package com.example.daboyeo_android.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.ItemReportBinding
import com.example.daboyeo_android.entity.home.ReportData

class ReportsAdapter(private var reports: List<ReportData>) : RecyclerView.Adapter<ReportsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportsViewHolder =
            ReportsViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.item_report,
                            parent,
                            false
                    )
            )

    override fun onBindViewHolder(holder: ReportsViewHolder, position: Int) {
        val view = holder.itemView
        val item: ReportData = reports[position]

        holder.binding.reportItem = reports[position]

        view.apply {
            holder.clickSympathy(item.report_id ,item.is_sympathy)
            holder.clickTag()
        }
    }

    override fun getItemCount() = reports.size

}