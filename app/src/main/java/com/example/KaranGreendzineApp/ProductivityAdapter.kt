package com.example.KaranGreendzineApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductivityAdapter (private val items: List<ProductivityData>):
    RecyclerView.Adapter<ProductivityAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.bind(item)
        }

        override fun getItemCount(): Int {
            return items.size
        }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val prodTextView: TextView = itemView.findViewById(R.id.tv_productivity)
        private val percentTextView: TextView = itemView.findViewById(R.id.tv_percentage)
        private val progressBar: ProgressBar = itemView.findViewById(R.id.pb_view)
        fun bind(productivityItem: ProductivityData) {
            prodTextView.text = productivityItem.prod
            percentTextView.text = "${productivityItem.percent}%"
            progressBar.progress = productivityItem.percent
        }
    }

}