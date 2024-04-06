package com.tookbook.ui.mainModel.shelf.adapter

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
import com.tookbook.databinding.ItemListPhotoBinding
import com.tookbook.databinding.ItemRecommendBinding
import com.tookbook.databinding.ItemShelfBinding
import java.util.*


class ShelfAdapter(
    private val mList: ArrayList<Int>,
    var context: Context,
) :
    RecyclerView.Adapter<ShelfAdapter.ViewHolder>() {

    lateinit var binder: ItemShelfBinding

    class ViewHolder(@NonNull binding: ItemShelfBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var _binding: ItemShelfBinding = binding

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binder = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_shelf,
            parent,
            false
        )

        return ViewHolder(binder)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("RecyclerView", "SetTextI18n", "SimpleDateFormat", "NewApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList[position]

        holder._binding.ivMain.setImageResource(item)

    }

    override fun getItemCount(): Int {
        return mList.size
    }

}