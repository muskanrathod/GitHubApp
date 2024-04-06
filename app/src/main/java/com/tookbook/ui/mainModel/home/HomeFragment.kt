package com.tookbook.ui.mainModel.home

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.tookbook.R
import com.tookbook.base.fragment.BaseFragment
import com.tookbook.databinding.FragmentHomeBinding
import com.tookbook.ui.mainModel.home.adapter.ViewPagerAdapter
import com.tookbook.ui.mainModel.home.donate.DonateFragment
import com.tookbook.ui.mainModel.home.recommend.RecommendFragment
import com.tookbook.ui.mainModel.home.rent.RentFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun getViewModel(): HomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

    override fun onReadyToRender(
        view: View,
        viewModel: HomeViewModel,
        binder: FragmentHomeBinding,
        savedInstanceState: Bundle?
    ) {
        setGreenStatusBar()
        initView(binder)
    }

    private fun initView(binder: FragmentHomeBinding){
        setupViewPager(binder.tabViewpager)
        binder.tabViewpager.disableScroll(true)
        binder.tabLayout.setupWithViewPager(binder.tabViewpager)
        for (i in 0 until binder.tabLayout.tabCount) {
            val tab = (binder.tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(dpToPx(8), 0, dpToPx(8), 0)
            tab.requestLayout()
        }
    }

    private fun setupViewPager(viewpager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)

        adapter.addFragment(RentFragment(), "Rent")
        adapter.addFragment(DonateFragment(), "Donate")
        adapter.addFragment(RecommendFragment(), "Recommendation")

        viewpager.adapter = adapter
        viewpager.beginFakeDrag()
    }

    private fun dpToPx(dp: Int): Int {
        return ((dp * Resources.getSystem().displayMetrics.density).toInt());
    }

    fun pxToDp(px: Int, context: Context): Int {
        return ((px / Resources.getSystem().displayMetrics.density).toInt());
    }

}