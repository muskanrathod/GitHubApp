package com.tookbook.ui.authModel.login

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Insets
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import androidx.lifecycle.ViewModelProvider
import com.tookbook.R
import com.tookbook.base.fragment.BaseFragment
import com.tookbook.databinding.FragmentLoginBinding
import com.tookbook.ui.mainModel.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_login

    override fun getViewModel(): LoginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

    override fun onReadyToRender(
        view: View,
        viewModel: LoginViewModel,
        binder: FragmentLoginBinding,
        savedInstanceState: Bundle?
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            setWhiteStatusBar()
        }
        initView(binder)
    }

    private fun initView(binder: FragmentLoginBinding) {
        binder.btnLogin.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            requireActivity().startActivity(intent)
            requireActivity().finish()
        }
    }

}