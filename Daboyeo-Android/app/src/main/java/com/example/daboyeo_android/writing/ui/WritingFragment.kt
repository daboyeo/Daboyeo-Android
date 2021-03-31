package com.example.daboyeo_android.writing.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.FragmentWritingBinding
import java.lang.Exception

class WritingFragment : Fragment() {
    private lateinit var binding : FragmentWritingBinding
    private val OPEN_IMAGE = 300
    private val OPEN_LOCATION = 200

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_writing, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.writingLocationButton.setOnClickListener {
            val intent = Intent(context, LocationActivity::class.java)
            startActivityForResult(intent, OPEN_LOCATION)
        }

        binding.writingImageButton.setOnClickListener {
            val intent = Intent(Intent.EXTRA_ALLOW_MULTIPLE)
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "1 ~ 10장을 선택해주세요"), OPEN_IMAGE
            )
        }

    }

    override fun startActivityForResult(data: Intent?, requestCode: Int) {
        super.startActivityForResult(data, requestCode)

        if(requestCode == OPEN_IMAGE) {
            try {

            } catch (e: Exception) {
                println(e.toString())
            }
        } else if (requestCode == OPEN_LOCATION){

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == OPEN_IMAGE && requestCode == RESULT_OK) {
            if(data?.clipData != null) {
                val count = data.clipData!!.itemCount
                if(count > 11) {
                    Toast.makeText(context, getString(R.string.over_image), Toast.LENGTH_SHORT).show()
                    return
                }
                for(i in 0 until count) {
                    var imageUri = data.clipData!!.getItemAt(i).uri

                }
            } else {
                data?.data?.let {
                    
                }
            }
        }
    }

}