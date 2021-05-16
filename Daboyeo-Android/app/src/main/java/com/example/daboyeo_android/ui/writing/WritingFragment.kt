package com.example.daboyeo_android.ui.writing

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.FragmentWritingBinding
import com.example.daboyeo_android.ui.home.HomeActivity
import java.lang.Exception

private const val REQUEST_LOCATION = 200
private const val REQUEST_OPEN_GALLERY = 300

class WritingFragment : Fragment() {
    private lateinit var binding: FragmentWritingBinding
    private lateinit var location: String
    private var viewModel = WritingViewModel()

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
            startActivity(intent)
        }

        binding.writingImageButton.setOnClickListener {
            val intent = Intent()
            //intent.type = "image/*"
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "사진 최대 10장 선택가능"),
                REQUEST_OPEN_GALLERY
            )
        }

        binding.writingSendButton.setOnClickListener {
            viewModel.writingReport(binding.writingContentEditView.text.toString(), location)
        }
    }

    override fun startActivityForResult(data: Intent?, requestCode: Int) {
        super.startActivityForResult(data, requestCode)

        if (requestCode == REQUEST_LOCATION){

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == REQUEST_OPEN_GALLERY) {
            if (data?.clipData != null) {
                binding.writingFramelayout.visibility = View.VISIBLE

                val count = data.clipData!!.itemCount
                if (count > 10) {
                    Toast.makeText(context, getString(R.string.select_image), Toast.LENGTH_SHORT)
                        .show()
                    return
                }

                viewModel.imageUriList.clear()
                for (i in 0 until count) {
                    val imageUri = data.clipData!!.getItemAt(i).uri
                    viewModel.imageUriList.add(imageUri)
                }
                binding.writingImageImageView.setImageURI(data.clipData!!.getItemAt(0).uri)

                if (count > 1) {
                    binding.writingImagesTextView.let {
                        it.visibility = View.VISIBLE
                        it.text = "외 " + (count - 1) + "장"
                    }
                } else {
                    binding.writingImagesTextView.visibility = View.GONE
                }
            } else {
                data?.data?.let { uri ->
                    viewModel.imageUriList.clear()
                    viewModel.imageUriList.add(uri)
                    binding.writingImageImageView.setImageURI(uri)
                    binding.writingImagesTextView.visibility = View.GONE
                }
            }
        }
    }
}