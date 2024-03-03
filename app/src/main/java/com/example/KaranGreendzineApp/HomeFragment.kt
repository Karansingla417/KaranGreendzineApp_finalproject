package com.example.KaranGreendzineApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val productivityDataList = Utils.loadProductivityData(requireContext())

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.rv_productivity)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter =
            ProductivityAdapter(productivityDataList)
        return view
    }
}