package com.daniel.android.demo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.daniel.android.demo.databinding.ActivityMainBinding
import com.daniel.android.demo.databinding.ItemButtonBinding
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider
import kotlin.reflect.KClass

class MainActivity : BaseActivity(), AnkoLogger {

    @Inject
    lateinit var list: List<@JvmSuppressWildcards Pair<Int, KClass<out Activity>>>

    @Inject
    lateinit var ts: Provider<Long>

    @Inject
    @field:Named("hashcode")
    lateinit var hashCode: Provider<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.listView.adapter = ItemAdapter()
    }

    inner class ItemAdapter : RecyclerView.Adapter<ItemViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemButtonBinding.inflate(inflater, parent, false)
            return ItemViewHolder(binding)
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bind(list[position].first, list[position].second)
        }

    }

    inner class ItemViewHolder(private val binding: ItemButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun <T : Any> bind(@StringRes content: Int, clazz: KClass<T>) {
            with(binding) {
                button.setText(content)
                button.setOnClickListener {
                    startActivity(Intent(this@MainActivity, clazz.java).putExtra("ts", ts.get()))
                    info { "timestamp: ${ts.get()}, hashcode: ${hashCode.get()}" }
                }
            }
        }
    }
}
