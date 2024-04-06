package com.tookbook.ui.mainModel.bookDetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.tookbook.R

class ViewAdapter(var images: IntArray, var context: Context): PagerAdapter() {
    private lateinit var inflater: LayoutInflater

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.list_view_detail, container, false)
        val image: ImageView = v.findViewById(R.id.iv_main)
        image.setImageResource(images[position])
        container.addView(v)
        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val v = `object`as View
        container.removeView(v)
    }
}