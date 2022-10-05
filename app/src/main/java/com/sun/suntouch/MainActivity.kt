package com.sun.suntouch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val componentContainer = findViewById<ComponentContainer>(R.id.component_container).apply {
            headView = findViewById(R.id.head)
            hangingView = findViewById(R.id.hanging)
            contentView = findViewById<ViewPager>(R.id.view_pager).apply {
                adapter = object : PagerAdapter() {
                    override fun getCount() = 1

                    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

                    override fun instantiateItem(container: ViewGroup, position: Int): Any {
                        val recyclerView = RecyclerView(this@MainActivity)
                        recyclerView.layoutManager = LinearLayoutManager(context)
                        recyclerView.adapter =  object: RecyclerView.Adapter<SunViewHolder>() {
                            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SunViewHolder {
                                return SunViewHolder(TextView(context).apply {
                                    text = "1"
                                    minHeight = 100
                                    textSize = 20f
                                })
                            }
                            override fun onBindViewHolder(holder: SunViewHolder, position: Int) {
                                (holder.itemView as TextView).text = position.toString()
                            }

                            override fun getItemCount() = 100
                        }
                        container.addView(recyclerView)
                        return recyclerView
                    }
                }
            }
            headView?.post {
                doBinding()
            }
        }
    }
}

class SunViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}