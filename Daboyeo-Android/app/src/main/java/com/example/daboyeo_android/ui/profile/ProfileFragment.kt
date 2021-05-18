package com.example.daboyeo_android.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.FragmentProfileBinding
import com.example.daboyeo_android.ui.home.HomeActivity

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: ProfileViewModel by viewModels()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.getMyProfile()

        return binding.root
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