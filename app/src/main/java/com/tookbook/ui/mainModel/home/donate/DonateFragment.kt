package com.tookbook.ui.mainModel.home.donate

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tookbook.R
import com.tookbook.base.fragment.BaseFragment
import com.tookbook.databinding.FragmentDonateBinding
import com.tookbook.ui.dialog.DialogPhoto
import com.tookbook.ui.mainModel.home.donate.adapter.PhotoAdapter
import com.tookbook.utils.BaseInterface
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream


@AndroidEntryPoint
class DonateFragment : BaseFragment<DonateViewModel, FragmentDonateBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_donate

    override fun getViewModel(): DonateViewModel =
        ViewModelProvider(this)[DonateViewModel::class.java]

    private var pList = ArrayList<String>()

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onReadyToRender(
        view: View,
        viewModel: DonateViewModel,
        binder: FragmentDonateBinding,
        savedInstanceState: Bundle?
    ) {
        initView(binder)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun initView(binder: FragmentDonateBinding) {
//        pList.clear()
//        pList.add(R.drawable.photo)
//        pList.add(R.drawable.photo_1)
//
//        val photoAdapter = PhotoAdapter(pList, requireContext())
//        val layoutManager =
//            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        binder.rvPhoto.layoutManager = layoutManager
//        binder.rvPhoto.adapter = photoAdapter

        if(pList.isEmpty()){
            binder.addPhoto1.visible()
        }

        binder.addPhoto1.setOnClickListener {
            if (isPermissionGranted()) {
                dialogPhoto(requireContext(), object : DialogPhoto.OnClickCallback {
                    override fun onSelectCamera() {
                        openCamera()
                    }

                    override fun onSelectUpload() {
                        openGallery()
                    }

                    override fun onDelete() {}
                })
            } else {
                checkCameraPermission()
            }
        }

        binder.addPhoto.setOnClickListener {
            if (isPermissionGranted()) {
                dialogPhoto(requireContext(), object : DialogPhoto.OnClickCallback {
                    override fun onSelectCamera() {
                        openCamera()
                    }

                    override fun onSelectUpload() {
                        openGallery()
                    }

                    override fun onDelete() {}
                })
            } else {
                checkCameraPermission()
            }
        }
    }

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) return
        when (requestCode) {
            BaseInterface.GALLERY -> try {
                val uri = data?.data
                binder!!.addPhoto1.gone()
                binder!!.addPhoto.visible()
                pList.add(uri.toString())

                val photoAdapter = PhotoAdapter(pList, requireContext())
                val layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binder!!.rvPhoto.layoutManager = layoutManager
                binder!!.rvPhoto.adapter = photoAdapter

                val source = ImageDecoder.createSource(requireActivity().contentResolver, uri!!)
                val bitmap = ImageDecoder.decodeBitmap(source)

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireActivity(), "Gallery Error", Toast.LENGTH_LONG).show()
            }
            BaseInterface.CAMERA -> try {
                val bitmap = data?.extras?.get("data") as Bitmap
                val uri = getImageUri(bitmap)

                binder!!.addPhoto1.gone()
                binder!!.addPhoto.visible()
                pList.add(uri.toString())

                val photoAdapter = PhotoAdapter(pList, requireContext())
                val layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binder!!.rvPhoto.layoutManager = layoutManager
                binder!!.rvPhoto.adapter = photoAdapter

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireActivity(), "Camera Error", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getImageUri(inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            requireContext().contentResolver,
            inImage,
            "Title",
            null
        )
        return Uri.parse(path)
    }

}