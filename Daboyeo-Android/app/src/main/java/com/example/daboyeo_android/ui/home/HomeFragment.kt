package com.example.daboyeo_android.ui.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        return binding.root
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