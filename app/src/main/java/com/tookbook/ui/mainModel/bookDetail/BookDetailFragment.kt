package com.tookbook.ui.mainModel.bookDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.tookbook.R
import com.tookbook.base.fragment.BaseFragment
import com.tookbook.databinding.FragmentBookDetailBinding
import com.tookbook.ui.authModel.intro.adapter.ViewPagerAdapter
import com.tookbook.ui.mainModel.bookDetail.adapter.ViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailFragment : BaseFragment<BookDetailViewModel, FragmentBookDetailBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_book_detail

    private var viewAdapter: ViewAdapter? = null
    private var images: IntArray? = null

    override fun getViewModel(): BookDetailViewModel = ViewModelProvider(this)[BookDetailViewModel::class.java]

    override fun onReadyToRender(
        view: View,
        viewModel: BookDetailViewModel,
        binder: FragmentBookDetailBinding,
        savedInstanceState: Bundle?
    ) {
        setWhiteStatusBar()
        initView(binder)
    }

    private fun initView(binder: FragmentBookDetailBinding) {
        binder.header.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binder.btnCart.setOnClickListener {
            findNavController().navigate(R.id.action_bookDetailFragment_to_orderPlacedFragment)
        }

        images = intArrayOf(
            R.drawable.cover_1,
            R.drawable.cover_1
        )
        viewAdapter = ViewAdapter(images!!, requireContext())
        binder.viewPager.adapter = viewAdapter
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
    }

}