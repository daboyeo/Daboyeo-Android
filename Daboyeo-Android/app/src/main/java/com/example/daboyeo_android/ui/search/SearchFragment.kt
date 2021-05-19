package com.example.daboyeo_android.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.FragmentSearchBinding
import com.example.daboyeo_android.ui.home.adapter.ReportsAdapter

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.searchSearchButton.setOnClickListener {
            getReports()
        }
    }

    private fun getReports() {
        viewModel.getReports(binding.searchContentEditText.text.toString())
        viewModel.reportsData.observe(viewLifecycleOwner, {
            if(it.reports.isEmpty()) {
                binding.searchRecyclerView.visibility = View.GONE
                binding.searchCommentTextView.visibility = View.VISIBLE
            } else {
                binding.searchRecyclerView.setHasFixedSize(true)
                binding.searchRecyclerView.adapter = ReportsAdapter(it.reports)
            }
        })
    }

}