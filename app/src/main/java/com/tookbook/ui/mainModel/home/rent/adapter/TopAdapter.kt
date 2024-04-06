package com.tookbook.ui.mainModel.home.rent.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tookbook.R
import com.tookbook.data.model.DiscoverDTO
import com.tookbook.data.model.OrderDTO
import com.tookbook.data.model.TopDTO
import com.tookbook.databinding.ItemListDiscoverBinding
import com.tookbook.databinding.ItemListTopBinding
import com.tookbook.ui.mainModel.orders.adapter.OrderAdapter
import java.util.*


class TopAdapter(
    private val mList: ArrayList<TopDTO>,
    var context: Context,
    private val cellClickListener: CellClickListener
) :
    RecyclerView.Adapter<TopAdapter.ViewHolder>() {

    lateinit var binder: ItemListTopBinding

    class ViewHolder(@NonNull binding: ItemListTopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var _binding: ItemListTopBinding = binding

    }

    interface CellClickListener {
        fun onCellClickListener(itemsOne: TopDTO)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binder = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_list_top,
            parent,
            false
        )

        return ViewHolder(binder)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("RecyclerView", "SetTextI18n", "SimpleDateFormat", "NewApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList[position]

        holder._binding.ivMain.setImageResource(item.image)
        holder._binding.tvTitle.text = item.title

        holder._binding.cardContainer.setOnClickListener {
            cellClickListener.onCellClickListener(item)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

}