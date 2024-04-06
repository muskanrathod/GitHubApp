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
import com.tookbook.databinding.ItemListDiscoverBinding
import java.util.*


class DiscoverAdapter(
    private val mList: ArrayList<DiscoverDTO>,
    var context: Context,
) :
    RecyclerView.Adapter<DiscoverAdapter.ViewHolder>() {

    lateinit var binder: ItemListDiscoverBinding

    class ViewHolder(@NonNull binding: ItemListDiscoverBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var _binding: ItemListDiscoverBinding = binding

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binder = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_list_discover,
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
        holder._binding.tvDesc.text = item.desc

//        if(position == 0){
//            val layoutParams = FrameLayout.LayoutParams(350, 550)
//            holder._binding.ivMain.layoutParams = layoutParams
//            holder._binding.tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,46F)
//            holder._binding.tvDesc.setTextSize(TypedValue.COMPLEX_UNIT_PX,40F)
//        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

}