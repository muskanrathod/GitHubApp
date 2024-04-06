package com.tookbook.ui.mainModel.home.donate.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
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
import java.util.*
import kotlin.collections.ArrayList


class PhotoAdapter(
    private val mList: ArrayList<String>,
    var context: Context,
) :
    RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    lateinit var binder: ItemListPhotoBinding

    class ViewHolder(@NonNull binding: ItemListPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var _binding: ItemListPhotoBinding = binding

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binder = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_list_photo,
            parent,
            false
        )

        return ViewHolder(binder)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("RecyclerView", "SetTextI18n", "SimpleDateFormat", "NewApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
        holder._binding.ivOne.setImageURI(Uri.parse(item))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}