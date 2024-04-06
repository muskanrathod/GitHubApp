package com.tookbook.ui.mainModel.shelf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.tookbook.R
import com.tookbook.base.fragment.BaseFragment
import com.tookbook.databinding.FragmentShelfBinding
import com.tookbook.ui.mainModel.shelf.adapter.ShelfAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShelfFragment : BaseFragment<ShelfViewModel, FragmentShelfBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_shelf

    val mList = ArrayList<Int>()

    override fun getViewModel(): ShelfViewModel = ViewModelProvider(this)[ShelfViewModel::class.java]

    override fun onReadyToRender(
        view: View,
        viewModel: ShelfViewModel,
        binder: FragmentShelfBinding,
        savedInstanceState: Bundle?
    ) {
        setWhiteStatusBar()
        initView(binder)
    }

    private fun initView(binder: FragmentShelfBinding) {
        mList.clear()
        mList.add(R.drawable.cover_1)
        mList.add(R.drawable.cover_2)
        mList.add(R.drawable.cover_3)
        mList.add(R.drawable.cover_4)
        mList.add(R.drawable.cover_5)
        mList.add(R.drawable.cover_1)
        mList.add(R.drawable.cover_2)
        mList.add(R.drawable.cover_3)
        mList.add(R.drawable.cover_4)
        mList.add(R.drawable.cover_5)
        mList.add(R.drawable.cover_1)
        mList.add(R.drawable.cover_2)
        mList.add(R.drawable.cover_3)
        mList.add(R.drawable.cover_4)
        mList.add(R.drawable.cover_5)


        val shelfAdapter = ShelfAdapter(mList, requireContext())
        val layoutManager = GridLayoutManager(requireContext(), 3)
        binder.rvShelf.adapter = shelfAdapter
        binder.rvShelf.layoutManager = layoutManager
    }

}