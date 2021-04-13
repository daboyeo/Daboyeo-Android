package com.example.daboyeo_android.ui.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.FragmentModifyProfileBinding
import com.example.daboyeo_android.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

private const val REQUEST_OPEN_GALLERY = 200

@AndroidEntryPoint
class ModifyProfileFragment : Fragment() {
    private lateinit var binding: FragmentModifyProfileBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_modify_profile, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.modifyUserImgImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, REQUEST_OPEN_GALLERY)
        }

        binding.modifyModifyButton.setOnClickListener {
            (activity as HomeActivity).replaceFragment(ProfileFragment())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_OPEN_GALLERY && requestCode == RESULT_OK) {
            if (data?.data != null) {

            }
        }
    }
}