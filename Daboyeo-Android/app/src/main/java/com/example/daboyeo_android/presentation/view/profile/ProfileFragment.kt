package com.example.daboyeo_android.presentation.view.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.FragmentProfileBinding
import com.example.daboyeo_android.presentation.viewModel.ProfileViewModel
import com.example.daboyeo_android.presentation.view.home.HomeActivity
import com.example.daboyeo_android.presentation.view.home.adapter.OnItemClickListener
import com.example.daboyeo_android.presentation.view.home.adapter.PostsAdapter
import com.example.daboyeo_android.presentation.view.post.DetailPostFragment
import com.example.daboyeo_android.presentation.viewModel.ProfileViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    lateinit var viewModel: ProfileViewModel
    @Inject
    lateinit var factory: ProfileViewModelFactory
    @Inject
    lateinit var adapter: PostsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        viewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        viewModel.getMyProfile()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.profilePosts.observe(viewLifecycleOwner, {
            binding.profileRecyclerview.setHasFixedSize(true)
            binding.profileRecyclerview.adapter = adapter.apply {
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
        binding.profileToolbar.inflateMenu(R.menu.profile_toolbar)
        binding.profileToolbar.title = getString(R.string.my_profile)

        binding.profileToolbar.setOnMenuItemClickListener{
            when(it.itemId) {
                R.id.profile_toolbar_modify -> {
                    (activity as HomeActivity).replaceFragment(ModifyProfileFragment())
                    true
                }
                else -> false
            }
        }
    }
}