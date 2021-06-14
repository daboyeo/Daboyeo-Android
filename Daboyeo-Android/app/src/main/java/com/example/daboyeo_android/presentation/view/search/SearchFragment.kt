package com.example.daboyeo_android.presentation.view.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.FragmentSearchBinding
import com.example.daboyeo_android.presentation.viewModel.SearchViewModel
import com.example.daboyeo_android.presentation.view.home.HomeActivity
import com.example.daboyeo_android.presentation.view.home.adapter.OnItemClickListener
import com.example.daboyeo_android.presentation.view.home.adapter.PostsAdapter
import com.example.daboyeo_android.presentation.view.post.DetailPostFragment
import com.example.daboyeo_android.presentation.viewModel.SearchViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    @Inject
    lateinit var factory: SearchViewModelFactory
    lateinit var viewModel: SearchViewModel
    @Inject
    lateinit var adapter: PostsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.searchSearchButton.setOnClickListener {
            getReports()
        }

    }

    private fun getReports() {
        viewModel.getPosts(binding.searchContentEditText.text.toString())

        viewModel.reportsData.observe(viewLifecycleOwner, {
            binding.searchRecyclerView.visibility = View.VISIBLE
            binding.searchRecyclerView.setHasFixedSize(true)
            binding.searchRecyclerView.adapter = adapter.apply {
                setList(it.posts)
                setItemClickListener(object : OnItemClickListener {
                    override fun onItemClick(v: View, reportId: Int) {
                        (activity as HomeActivity).replaceFragment(DetailPostFragment(),reportId)
                    }

                })
            }
        })
    }

}