package com.tookbook.ui.mainModel.home.rent

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import androidx.viewpager.widget.ViewPager
import com.tookbook.R
import com.tookbook.base.fragment.BaseFragment
import com.tookbook.data.model.DiscoverDTO
import com.tookbook.data.model.TopDTO
import com.tookbook.databinding.FragmentRentBinding
import com.tookbook.ui.authModel.intro.adapter.ViewPagerAdapter
import com.tookbook.ui.mainModel.home.rent.adapter.DiscoverAdapter
import com.tookbook.ui.mainModel.home.rent.adapter.TopAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RentFragment : BaseFragment<RentViewModel, FragmentRentBinding>(), TopAdapter.CellClickListener {
    override val layoutRes: Int
        get() = R.layout.fragment_rent

    override fun getViewModel(): RentViewModel = ViewModelProvider(this)[RentViewModel::class.java]

    var dList = ArrayList<DiscoverDTO>()
    var tList = ArrayList<TopDTO>()
    private var viewPagerAdapter: ViewPagerAdapter? = null
    private var layouts: IntArray? = null

    override fun onReadyToRender(
        view: View,
        viewModel: RentViewModel,
        binder: FragmentRentBinding,
        savedInstanceState: Bundle?
    ) {
        initView(binder)
    }

    private fun initView(binder: FragmentRentBinding) {
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

        dList.clear()
        dList.add(DiscoverDTO(R.drawable.cover_1, "Book Title 1", "Description 1"))
        dList.add(DiscoverDTO(R.drawable.cover_2, "Book Title 2", "Description 2"))
        dList.add(DiscoverDTO(R.drawable.cover_3, "Book Title 3", "Description 3"))
        dList.add(DiscoverDTO(R.drawable.cover_4, "Book Title 4", "Description 4"))
        dList.add(DiscoverDTO(R.drawable.cover_5, "Book Title 5", "Description 5"))

        val discoverAdapter = DiscoverAdapter(dList, requireContext())
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binder.rvDiscover.layoutManager = layoutManager
        binder.rvDiscover.adapter = discoverAdapter

        tList.clear()
        tList.add(TopDTO(R.drawable.cover_1, "Book Title 1"))
        tList.add(TopDTO(R.drawable.cover_2, "Book Title 2"))
        tList.add(TopDTO(R.drawable.cover_3, "Book Title 3"))
        tList.add(TopDTO(R.drawable.cover_4, "Book Title 4"))
        tList.add(TopDTO(R.drawable.cover_5, "Book Title 5"))

        val topAdapter = TopAdapter(tList, requireContext(), this)
        val layoutManager1 = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binder.rvTop.layoutManager = layoutManager1
        binder.rvTop.adapter = topAdapter

    }

    override fun onCellClickListener(itemsOne: TopDTO) {
        findNavController().navigate(R.id.action_homeFragment_to_bookDetailFragment)
    }

}