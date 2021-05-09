package com.example.daboyeo_android.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: HomeViewModel by viewModels()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }
}