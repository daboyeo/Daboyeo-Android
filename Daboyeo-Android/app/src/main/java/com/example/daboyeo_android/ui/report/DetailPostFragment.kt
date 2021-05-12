package com.example.daboyeo_android.ui.report

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.FragmentDetailPostBinding
import com.example.daboyeo_android.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

class DetailPostFragment : Fragment() {
    private lateinit var binding: FragmentDetailPostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_post, container, false)

        spinnerInit()

        return binding.root
    }

    private fun spinnerInit() {
        binding.detailSelectSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when(position) {
                        1 -> {
                            (activity as HomeActivity).changeView(ModifyReportFragment(), "Writing")
                        }
                        2 -> {

                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

        }
    }

}

