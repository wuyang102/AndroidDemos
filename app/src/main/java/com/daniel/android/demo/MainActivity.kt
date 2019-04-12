package com.daniel.android.demo

import android.content.Intent
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daniel.android.demo.coordinate.CoordinateLayoutActivity
import kotlinx.android.extensions.LayoutContainer

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_button.*
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    val list = listOf(
        R.string.coordinate_layout to CoordinateLayoutActivity::class
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView.adapter = ItemAdapter()
    }

    inner class ItemAdapter : RecyclerView.Adapter<ItemViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_button, parent, false)
            return ItemViewHolder(view)
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bind(list[position].first, list[position].second)
        }

    }

    inner class ItemViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun <T : Any> bind(@StringRes content: Int, clazz: KClass<T>) {
            button.setText(content)
            button.setOnClickListener {
                startActivity(Intent(this@MainActivity, clazz.java)) }
        }
    }
}
