package com.tookbook.ui.authModel.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.tookbook.R
import com.tookbook.base.fragment.BaseFragment
import com.tookbook.databinding.FragmentLoginBinding
import com.tookbook.databinding.FragmentSignupBinding
import com.tookbook.ui.mainModel.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : BaseFragment<SignupViewModel, FragmentSignupBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_signup

    override fun getViewModel(): SignupViewModel = ViewModelProvider(this)[SignupViewModel::class.java]

    override fun onReadyToRender(
        view: View,
        viewModel: SignupViewModel,
        binder: FragmentSignupBinding,
        savedInstanceState: Bundle?
    ) {
        setWhiteStatusBar()
        initView(binder)
    }

    private fun initView(binder: FragmentSignupBinding) {
        binder.btnSignup.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            requireActivity().startActivity(intent)
            requireActivity().finish()
        }
    }

}