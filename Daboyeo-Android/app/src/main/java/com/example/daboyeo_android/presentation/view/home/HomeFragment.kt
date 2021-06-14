package com.example.daboyeo_android.presentation.view.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.FragmentHomeBinding
import com.example.daboyeo_android.presentation.viewModel.HomeViewModel
import com.example.daboyeo_android.presentation.view.home.adapter.OnItemClickListener
import com.example.daboyeo_android.presentation.view.home.adapter.PostsAdapter
import com.example.daboyeo_android.presentation.view.post.DetailPostFragment
import com.example.daboyeo_android.presentation.viewModel.HomeViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    @Inject
    lateinit var factory: HomeViewModelFactory
    lateinit var viewModel: HomeViewModel
    @Inject
    lateinit var adapter: PostsAdapter
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.reportsData.observe(viewLifecycleOwner, {
            binding.homeRecyclerView.setHasFixedSize(true)
            binding.homeRecyclerView.adapter = adapter.apply {
                setList(it.posts)
                setItemClickListener(object : OnItemClickListener {
                    override fun onItemClick(v: View, reportId: Int) {
                        (activity as HomeActivity).replaceFragment(DetailPostFragment(), reportId)
                    }

                })
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.homeToolbar.inflateMenu(R.menu.home_toolbar)
        binding.homeToolbar.title = getString(R.string.app_name)

        binding.homeToolbar.setOnMenuItemClickListener{
            when(it.itemId) {
                R.id.home_toolbar_notifications -> {
                    Toast.makeText(context, "서비스 준비중입니다.",Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

}