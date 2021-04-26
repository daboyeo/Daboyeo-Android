package com.example.daboyeo_android.ui.sign

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.FragmentSignBinding

class SignFragment : Fragment() {
    private lateinit var binding: FragmentSignBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.signLoginButton.setOnClickListener {

        }
    }
}