package com.example.daboyeo_android.presentation.view.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.daboyeo_android.R
import java.io.File


class ViewPagerAdapter(private val context: Context, private val imageList: List<File>) :
    PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.fragment_detail_post, null)
        val imageView = view.findViewById<View>(R.id.viewPager_imageView) as ImageView?

        if (imageView != null) {
            Glide.with(context).load(imageList[position]).into(imageView)
        }
        container.addView(view)
        return view
    }

    override fun getCount(): Int {
        return imageList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view === o as View
    }

}
