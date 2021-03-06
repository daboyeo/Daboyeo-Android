package com.example.daboyeo_android.presentation.view.post

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.FragmentAddPostBinding
import com.example.daboyeo_android.presentation.viewModel.AddPostViewModel
import com.example.daboyeo_android.presentation.viewModel.AddPostViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val REQUEST_LOCATION = 200
private const val REQUEST_OPEN_GALLERY = 300

@AndroidEntryPoint
class WritingFragment : Fragment() {
    private lateinit var binding: FragmentAddPostBinding
    private lateinit var location: String

    @Inject
    lateinit var factory: AddPostViewModelFactory
    lateinit var viewModel: AddPostViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_post, container, false)

        viewModel = ViewModelProvider(this, factory).get(AddPostViewModel::class.java)

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
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                    Intent.createChooser(intent, "?????? ?????? 10??? ????????????"),
                    REQUEST_OPEN_GALLERY
            )
        }

        binding.writingSendButton.setOnClickListener {
            var contentResolver = requireContext().contentResolver
            location = "36.3908418290076, 127.36547532668905"
            viewModel.addPost(binding.writingContentEditView.text.toString(), location, contentResolver)
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
                    Log.e("WritingFragment", imageUri.toString())
                }
                binding.writingImageImageView.setImageURI(data.clipData!!.getItemAt(0).uri)

                if (count > 1) {
                    binding.writingImagesTextView.let {
                        it.visibility = View.VISIBLE
                        it.text = "??? " + (count - 1) + "???"
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