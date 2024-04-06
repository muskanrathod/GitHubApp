package com.tookbook.ui.authModel.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.tookbook.R
import com.tookbook.base.activity.BaseActivity
import com.tookbook.databinding.ActivitySplashBinding
import com.tookbook.ui.authModel.intro.IntroActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {

    override fun getViewModel(): SplashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]

    override val layoutRes: Int
        get() = R.layout.activity_splash

    override fun onReadyToRender(
        binder: ActivitySplashBinding,
        mViewModel: SplashViewModel,
        savedInstanceState: Bundle?
    ) {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        delayScreen()
    }

    private fun delayScreen() {
        val handler = Handler()
        handler.postDelayed(
            {
                openActivity(IntroActivity::class.java)
                finishAfterTransition()
            }, 2500
        )
    }

}