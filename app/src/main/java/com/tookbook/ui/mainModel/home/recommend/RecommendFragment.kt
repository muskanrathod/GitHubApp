package com.tookbook.ui.mainModel.home.recommend

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.tookbook.R
import com.tookbook.base.fragment.BaseFragment
import com.tookbook.databinding.FragmentDonateBinding
import com.tookbook.databinding.FragmentRecommendBinding
import com.tookbook.ui.authModel.intro.adapter.ViewPagerAdapter
import com.tookbook.ui.mainModel.home.recommend.adapter.PhotoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecommendFragment : BaseFragment<RecommendViewModel, FragmentRecommendBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_recommend

    override fun getViewModel(): RecommendViewModel =
        ViewModelProvider(this)[RecommendViewModel::class.java]

    private var viewPagerAdapter: ViewPagerAdapter? = null
    private var layouts: IntArray? = null
    var pList = ArrayList<Int>()

    override fun onReadyToRender(
        view: View,
        viewModel: RecommendViewModel,
        binder: FragmentRecommendBinding,
        savedInstanceState: Bundle?
    ) {
        initView(binder)
    }

    private fun initView(binder: FragmentRecommendBinding) {
        layouts = intArrayOf(
            R.layout.recommend_photo_list,
            R.layout.recommend_photo_list,
            R.layout.recommend_photo_list
        )
        viewPagerAdapter = ViewPagerAdapter(layouts!!, requireContext())
        binder.viewPager.adapter = viewPagerAdapter
        binder.dotLayout.setViewPager(binder.viewPager)
        binder.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {

            }

            override fun onPageSelected(position: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
        pList.clear()
        pList.add(R.drawable.cover_1)
        pList.add(R.drawable.cover_2)
        pList.add(R.drawable.cover_3)
        pList.add(R.drawable.cover_4)
        pList.add(R.drawable.cover_5)
        pList.add(R.drawable.cover_1)
        pList.add(R.drawable.cover_2)
        pList.add(R.drawable.cover_3)
        pList.add(R.drawable.cover_4)
        pList.add(R.drawable.cover_5)
        pList.add(R.drawable.cover_1)
        pList.add(R.drawable.cover_2)
        pList.add(R.drawable.cover_3)
        pList.add(R.drawable.cover_4)
        pList.add(R.drawable.cover_5)

        val photoAdapter = PhotoAdapter(pList, requireContext())
        val layoutManager = GridLayoutManager(requireContext(), 3)
        binder.rvGrid.layoutManager = layoutManager
        binder.rvGrid.adapter = photoAdapter
    }

}