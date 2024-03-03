package com.example.KaranGreendzineApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter

class EmployeeAdapter : ListAdapter<EmployeeDataItem, EmployeeAdapter.EmployeeViewHolder>(DiffCallback) {

    class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val empIdTextView: TextView = itemView.findViewById(R.id.emp_id)
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        val dobTextView: TextView = itemView.findViewById(R.id.dob)
        val roleTextView: TextView = itemView.findViewById(R.id.role)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclercardview_item, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.empIdTextView.text = "    ${currentItem.empId}"
        holder.nameTextView.text = "    ${currentItem.name}"
        holder.dobTextView.text = "    ${currentItem.date}"
        holder.roleTextView.text = "    ${currentItem.role}"
    }

    object DiffCallback : DiffUtil.ItemCallback<EmployeeDataItem>() {
        override fun areItemsTheSame(
            oldItem: EmployeeDataItem,
            newItem: EmployeeDataItem
        ): Boolean {
            return oldItem.empId == newItem.empId
        }

        override fun areContentsTheSame(
            oldItem: EmployeeDataItem,
            newItem: EmployeeDataItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}
