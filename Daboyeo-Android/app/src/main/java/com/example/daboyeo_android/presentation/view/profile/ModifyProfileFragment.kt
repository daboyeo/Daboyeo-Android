package com.example.daboyeo_android.presentation.view.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.FragmentModifyProfileBinding
import com.example.daboyeo_android.presentation.viewModel.ProfileViewModel
import com.example.daboyeo_android.presentation.view.home.HomeActivity
import com.example.daboyeo_android.presentation.viewModel.ProfileViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

private const val REQUEST_OPEN_GALLERY = 200

@AndroidEntryPoint
class ModifyProfileFragment : Fragment() {
    private lateinit var binding: FragmentModifyProfileBinding
    private var imageFile: File? = null
    lateinit var viewModel: ProfileViewModel
    @Inject
    lateinit var factory: ProfileViewModelFactory

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_modify_profile, container, false)
        imageFile = File(Environment.getExternalStorageDirectory(), "profile.png")

        viewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.getMyProfile()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.modifyUserImgImageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, REQUEST_OPEN_GALLERY)
        }

        binding.modifyModifyButton.setOnClickListener {
            viewModel.modifyProfile(binding.modifyNameEditView.text.toString(),imageFile!!)
            (activity as HomeActivity).replaceFragment(ProfileFragment())
        }

        binding.modifyToolbarBackButton.setOnClickListener {
            (activity as HomeActivity).replaceFragment(ProfileFragment())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_OPEN_GALLERY && requestCode == RESULT_OK) {
            if (data?.data != null) {
                var imageUri = data.data

                imageFile!!.delete()
                imageFile!!.createNewFile()

                val out = FileOutputStream(imageFile)
                context?.contentResolver?.openInputStream(imageUri!!)?.copyTo(out)
                out.close()
            }
        }
    }
}