package com.tookbook.base.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.tookbook.R
import com.tookbook.base.activity.BaseActivity
import com.tookbook.base.viewModel.BaseViewModel
import com.tookbook.ui.dialog.DialogPhoto
import com.tookbook.utils.BaseInterface
import java.util.regex.Pattern


abstract class BaseFragment<VM : BaseViewModel, T : ViewDataBinding> : Fragment() {

    protected var TAG: String = this.javaClass.simpleName
    private var mActivity: BaseActivity<VM, T>? = null
    lateinit var mContext: Context

    var binder: T? = null
    private lateinit var mViewModel: VM

    private var rootView: View? = null
    private var isFragmentLoaded = false

    var dialogPhoto: DialogPhoto? = null

    private val REQUEST_PERMISSION = 100
    var PERMISSIONS =
        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)

    @get:LayoutRes
    abstract val layoutRes: Int

    abstract fun getViewModel(): VM

    fun getDataBinder(): T {
        return this.binder!!
    }

    override fun onAttach(context: Context) {
        try {
            super.onAttach(context)
            if (context is BaseActivity<*, *>) {
                val activity = context as BaseActivity<VM, T>?
                this.mActivity = activity

                mContext = context
                //activity?.onFragmentAttached()
            }
        } catch (e: Throwable) {
            throw ClassCastException("$context must implement FragmentListener")
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binder = DataBindingUtil.inflate(inflater, layoutRes, container, false)!!
        binder?.lifecycleOwner = viewLifecycleOwner
        rootView = binder?.root!!
        return rootView!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            mViewModel.let { vm ->
                binder?.let { binder ->
                    onReadyToRender(view, vm, binder, savedInstanceState)
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binder = null
    }

    override fun onDetach() {
        super.onDetach()
        this.mActivity = null
    }

    override fun onDestroy() {
        super.onDestroy()
        isFragmentLoaded = false
    }

    fun getBaseActivity(): BaseActivity<VM, T>? {
        return this.mActivity
    }

    protected abstract fun onReadyToRender(
        view: View,
        viewModel: VM,
        binder: T,
        savedInstanceState: Bundle?,
    )


    fun launchOnLifecycleScope(execute: suspend () -> Unit) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            execute()
        }
    }

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
    fun Int.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this.toString())

    fun setWhiteStatusBar() {
        requireActivity().window.statusBarColor = resources.getColor(R.color.white)

        when (resources.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                setAppearanceLightStatusBars(true)
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                setAppearanceLightStatusBars(true)
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {}
        }
    }

    @SuppressLint("NewApi")
    fun setAppearanceLightStatusBars(isLight: Boolean) {
        if (isLight) {
            requireActivity().window.insetsController!!.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else {
            requireActivity().window.insetsController!!.setSystemBarsAppearance(
                0,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        }
    }

    fun setGreenStatusBar() {
        requireActivity().window.statusBarColor = resources.getColor(R.color.green)
    }

    fun View.gone() {
        visibility = View.GONE
    }

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.invisible() {
        visibility = View.INVISIBLE
    }

    open fun checkEmail(email: String?): Boolean {
        val EMAIL_ADDRESS_PATTERN: Pattern = Pattern
            .compile(
                "[a-zA-Z0-9+._%-+]{1,256}" + "@"
                        + "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" + "(" + "."
                        + "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" + ")+"
            )
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }

    fun openCamera() {
        try {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, BaseInterface.CAMERA)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireActivity(), e.message, Toast.LENGTH_LONG).show()
        }
    }

    fun openGallery() {
        try {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(Intent.createChooser(intent, ""), BaseInterface.GALLERY)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireActivity(), e.message, Toast.LENGTH_LONG).show()
        }
    }

    fun dialogPhoto(context: Context, param: DialogPhoto.OnClickCallback) {
        if (dialogPhoto != null && dialogPhoto!!.isShowing)
            dialogPhoto!!.dismiss()
        dialogPhoto = DialogPhoto(context)
        dialogPhoto!!.onClickCallback = param
        dialogPhoto!!.show()
    }


    @RequiresApi(Build.VERSION_CODES.R)
    fun isPermissionGranted(): Boolean {
        return !(ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        ) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.MANAGE_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun checkCameraPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            PERMISSIONS,
            REQUEST_PERMISSION
        )

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_PERMISSION -> {
                dialogPhoto(requireContext(), object : DialogPhoto.OnClickCallback {
                    override fun onSelectCamera() {
                        openCamera()
                    }

                    override fun onSelectUpload() {
                        openGallery()
                    }

                    override fun onDelete() {
                    }

                })
            }

        }
    }

}
