package com.tookbook.ui.authModel.intro

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.tookbook.R
import com.tookbook.base.activity.BaseActivity
import com.tookbook.databinding.ActivityIntroBinding
import com.tookbook.ui.authModel.AuthActivity
import com.tookbook.ui.authModel.intro.adapter.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroActivity : BaseActivity<IntroViewModel, ActivityIntroBinding>() {
    private var viewPagerAdapter: ViewPagerAdapter?= null
    private var layouts: IntArray?= null

    override fun getViewModel(): IntroViewModel = ViewModelProvider(this)[IntroViewModel::class.java]

    override val layoutRes: Int
        get() = R.layout.activity_intro

    override fun onReadyToRender(
        binder: ActivityIntroBinding,
        mViewModel: IntroViewModel,
        savedInstanceState: Bundle?
    ) {
        window.statusBarColor = resources.getColor(R.color.white)

        binder.nextBtn.visibility = View.VISIBLE
        binder.nextBtn.setOnClickListener{
            val currentPage : Int = binder.viewPager.currentItem +1
            binder.viewPager.currentItem = currentPage
        }

        binder.startBtn.setOnClickListener {
            startActivity(Intent(this@IntroActivity, AuthActivity::class.java))
            finish()
        }

        layouts = intArrayOf(R.layout.slider_1, R.layout.slider_2, R.layout.slider_3)
        viewPagerAdapter = ViewPagerAdapter(layouts!!, applicationContext)
        binder.viewPager.adapter = viewPagerAdapter
        binder.dotLayout.setViewPager(binder.viewPager)
        binder.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {

            }

            override fun onPageSelected(position: Int) {
                if (position == layouts!!.size - 1){
                    binder.nextBtn.visibility = View.GONE
                    binder.startBtn.visibility = View.VISIBLE
                }else{
                    binder.nextBtn.visibility = View.VISIBLE
                    binder.startBtn.visibility = View.GONE
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }
}