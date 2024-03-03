package com.example.KaranGreendzineApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import com.example.KaranGreendzineApp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var adapter: EmployeeAdapter
    private var employeeDataList: List<EmployeeDataItem> = emptyList()
    private lateinit var keyboardVisibilityUtils: KeyboardVisibilityUtils

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        recyclerView = binding.rvEmpDetails
        searchView = binding.searchView
        employeeDataList = Utils.loadEmployeeData(requireContext())

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = EmployeeAdapter()
        recyclerView.adapter = adapter

        adapter.submitList(employeeDataList)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return true
            }
        })
        return binding.root
    }

    private fun filter(query: String?) {
        val filteredList = mutableListOf<EmployeeDataItem>()
        if (query.isNullOrBlank()) {
            // If query is null or empty, show all items
            filteredList.addAll(employeeDataList)
        } else {
            // Filter items based on the query
            for (item in employeeDataList) {
                if (item.name.contains(query, ignoreCase = true)) {
                    filteredList.add(item)
                }
            }
        }
        adapter.submitList(filteredList)
    }
}

