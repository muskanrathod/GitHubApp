package com.githubapp.ui.mainModel.home.adapter

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
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.githubapp.R
import com.githubapp.data.model.Items
import com.githubapp.data.model.Owner
import com.githubapp.data.model.RepositoryDTO
import com.githubapp.databinding.ItemListBinding
import java.util.*
import kotlin.collections.ArrayList


class RepoListAdapter(
    private val mList: ArrayList<Items>,
    var context: Context,
    private val cellClickListener: CellClickListener
) :
    RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

    lateinit var binder: ItemListBinding

    class ViewHolder(@NonNull binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var _binding: ItemListBinding = binding

    }

    interface CellClickListener {
        fun onCellClickListener(itemsOne: Items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binder = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_list,
            parent,
            false
        )

        return ViewHolder(binder)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("RecyclerView", "SetTextI18n", "SimpleDateFormat", "NewApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
//        holder._binding.ivAvatar.setImageURI(Uri.parse(item.owner?.avatarUrl))
        Glide.with(context)
            .load(item.owner?.avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(holder._binding.ivAvatar)
        holder._binding.tvName.text = item.name
        holder._binding.tvDesc.text = item.description
        holder._binding.cv.setOnClickListener {
            cellClickListener.onCellClickListener(item)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}