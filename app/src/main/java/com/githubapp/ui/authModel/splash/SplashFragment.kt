package com.githubapp.ui.authModel.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.githubapp.R
import com.githubapp.base.fragment.BaseFragment
import com.githubapp.databinding.FragmentSplashBinding
import com.githubapp.ui.mainModel.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashViewModel, FragmentSplashBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_splash

    override fun getViewModel(): SplashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]

    override fun onReadyToRender(
        view: View,
        viewModel: SplashViewModel,
        binder: FragmentSplashBinding,
        savedInstanceState: Bundle?
    ) {
        setWhiteStatusBar()
        activity?.window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        delayScreen()
    }

    private fun delayScreen() {
        val handler = Handler()
        handler.postDelayed(
            {
                activity?.startActivity(Intent(mContext, MainActivity::class.java))
                activity?.finishAfterTransition()
            }, 2500
        )
    }

}