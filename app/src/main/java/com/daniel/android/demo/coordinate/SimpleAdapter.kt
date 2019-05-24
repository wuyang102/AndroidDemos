package com.daniel.android.demo.coordinate

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.daniel.android.demo.R
import javax.inject.Inject

/**
 * @author wuyang
 */
class SimpleAdapter @Inject constructor() : RecyclerView.Adapter<SimpleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
        return SimpleViewHolder(view as TextView)
    }

    override fun getItemCount(): Int = 100

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.setContent(position.toString())
    }

}

class SimpleViewHolder(private val view: TextView) : ViewHolder(view) {
    fun setContent(content: String) {
        view.text = content
    }
}