package com.example.daboyeo_android.presentation.view.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.daboyeo_android.R
import com.example.daboyeo_android.data.model.home.CommentData
import com.example.daboyeo_android.databinding.FragmentDetailPostBinding
import com.example.daboyeo_android.presentation.viewModel.PostViewModel
import com.example.daboyeo_android.presentation.view.home.HomeActivity
import com.example.daboyeo_android.presentation.view.home.HomeFragment
import com.example.daboyeo_android.presentation.view.home.adapter.ViewPagerAdapter
import com.example.daboyeo_android.presentation.view.post.adapter.CommentAdapter
import com.example.daboyeo_android.presentation.viewModel.PostViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailPostFragment : Fragment() {
    private lateinit var binding: FragmentDetailPostBinding
    private var reportId: Int = 0
    private var isLike = true
    lateinit var viewModel: PostViewModel
    @Inject
    lateinit var factory: PostViewModelFactory
    private var adapter = CommentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var extra = this.arguments
        if (extra != null) {
            extra = arguments
            if (extra != null) {
                reportId = extra.getInt("reportId")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_post, container, false)

        viewModel = ViewModelProvider(this, factory).get(PostViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        init()
        viewModel.getDetailReport(reportId)

        binding.detailLikeButton.setOnClickListener {
            clickLike()
            viewModel.like(reportId)
        }

        binding.detailSendButton.setOnClickListener {
            var comment = binding.detailCommentEditText.text.toString()
            viewModel.addComment(binding.detailCommentEditText.text.toString(), reportId)
        }

        binding.detailToolbarBackButton.setOnClickListener {
            (activity as HomeActivity).replaceFragment(HomeFragment())
        }

        viewModel.detailPost.observe(viewLifecycleOwner, {
            if (it.is_sympathy) {
                binding.detailFullLikeButton.visibility = View.VISIBLE
                binding.detailLikeButton.visibility = View.INVISIBLE
                this.isLike = true
            } else {
                binding.detailFullLikeButton.visibility = View.INVISIBLE
                binding.detailLikeButton.visibility = View.VISIBLE
                this.isLike = false
            }

            binding.detailContentTextView.text = it.content
            binding.detailContentTextView.append(" ")
            for (i in 0 until it.tags.size) {
                binding.detailContentTextView.append("#" + it.tags[i] + " ")
            }

            if (it.image_uris.isEmpty()) {
                binding.detailImageViewPager.visibility = View.GONE
            } else {
                binding.detailImageViewPager.visibility = View.GONE
                viewModel.getImage(it.image_uris)
                val adapter = ViewPagerAdapter(requireContext(), viewModel.uuid)
                binding.detailImageViewPager.adapter = adapter
            }

            binding.detailRecyclerView.setHasFixedSize(true)
            binding.detailRecyclerView.adapter = adapter.apply {
                setList(it.comments as ArrayList<CommentData>)
            }

        })

        return binding.root
    }

    private fun init() {
        binding.detailSelectSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        1 -> {

                        }
                        2 -> {
                            viewModel.deletePost(reportId)
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun clickLike() {
        if (isLike) {
            binding.detailFullLikeButton.visibility = View.INVISIBLE
            binding.detailLikeButton.visibility = View.VISIBLE

            this.isLike = false
        } else {
            binding.detailFullLikeButton.visibility = View.VISIBLE
            binding.detailLikeButton.visibility = View.INVISIBLE
            this.isLike = true
        }
    }
}