package com.tookbook.ui.mainModel.orders.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tookbook.R
import com.tookbook.data.model.OrderDTO
import com.tookbook.databinding.ItemListOrderBinding
import com.tookbook.databinding.ItemListPhotoBinding
import java.util.*
import kotlin.collections.ArrayList


class OrderAdapter(
    private val mList: ArrayList<OrderDTO>,
    var context: Context,
    private val cellClickListener: CellClickListener
) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    lateinit var binder: ItemListOrderBinding

    class ViewHolder(@NonNull binding: ItemListOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var _binding: ItemListOrderBinding = binding

    }

    interface CellClickListener {
        fun onCellClickListener(itemsOne: OrderDTO)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binder = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_list_order,
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
        holder._binding.tvAuthor.text = item.author
        holder._binding.tvNumberValue.text = item.number
        holder._binding.tvDateValue.text = item.date

        if(item.type == 0){
            holder._binding.tvStatusValue.text = "In-Transit"
        }else{
            holder._binding.tvStatusValue.text = "Delivered"
        }

        holder._binding.tvDetails.setOnClickListener {
            cellClickListener.onCellClickListener(item)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

}