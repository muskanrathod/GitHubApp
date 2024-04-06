package com.tookbook.ui.mainModel.orders

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tookbook.R
import com.tookbook.base.fragment.BaseFragment
import com.tookbook.data.model.OrderDTO
import com.tookbook.databinding.FragmentOrdersBinding
import com.tookbook.ui.mainModel.orders.adapter.OrderAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : BaseFragment<OrdersViewModel, FragmentOrdersBinding>(),
    OrderAdapter.CellClickListener {
    override val layoutRes: Int
        get() = R.layout.fragment_orders

    val mList = ArrayList<OrderDTO>()

    override fun getViewModel(): OrdersViewModel =
        ViewModelProvider(this)[OrdersViewModel::class.java]

    override fun onReadyToRender(
        view: View,
        viewModel: OrdersViewModel,
        binder: FragmentOrdersBinding,
        savedInstanceState: Bundle?
    ) {
        setWhiteStatusBar()
        binder.header.ivProfile.visible()

        binder.header.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        mList.clear()
        mList.add(
            OrderDTO(
                R.drawable.cover_1, "Invinsible Man", "Mark Goldman", "36X78", "26-July-2022",
                "In-Transit", 0
            )
        )
        mList.add(
            OrderDTO(
                R.drawable.cover_2, "Fault in Our Stars", "John Green", "36X78", "26-July-2022",
                "In-Transit", 0
            )
        )
        mList.add(
            OrderDTO(
                R.drawable.cover_3, "The Secret", "Rhonda Byrne", "36X78", "26-July-2022",
                "Delivered", 1
            )
        )
        mList.add(
            OrderDTO(
                R.drawable.cover_4, "Book of Night", "Holly Black", "36X78", "26-July-2022",
                "Delivered", 1
            )
        )

        val oList = mList.filter {
            it.type == 0
        }

        val hList = mList.filter {
            it.type == 1
        }

        setData(oList as ArrayList<OrderDTO>)

        binder.tvOrder.setOnClickListener {
            binder.tvOrder.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.font_30)
            )
            binder.tvHistory.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.font_20)
            )
            binder.tvOrder.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            binder.tvHistory.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_grey))
            setData(oList as ArrayList<OrderDTO>)
        }

        binder.tvHistory.setOnClickListener {
            binder.tvHistory.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.font_30)
            )
            binder.tvOrder.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.font_20)
            )
            binder.tvHistory.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            binder.tvOrder.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_grey))
            setData(hList as ArrayList<OrderDTO>)
        }
    }

    private fun setData(mList: ArrayList<OrderDTO>){
        val orderAdapter = OrderAdapter(mList, requireContext(), this)
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binder!!.rvOrder.adapter = orderAdapter
        binder!!.rvOrder.layoutManager = layoutManager
    }

    override fun onCellClickListener(itemsOne: OrderDTO) {

    }

}