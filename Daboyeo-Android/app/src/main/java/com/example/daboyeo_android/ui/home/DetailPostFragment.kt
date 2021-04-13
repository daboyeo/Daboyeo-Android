package com.example.daboyeo_android.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.FragmentDetailPostBinding
import dagger.hilt.android.AndroidEntryPoint

class DetailPostFragment : Fragment() {
    private lateinit var binding: FragmentDetailPostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_post, container, false)

        return binding.root
    }

}