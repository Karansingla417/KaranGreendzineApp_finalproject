package com.example.KaranGreendzineApp

// Utils.kt

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

object Utils {

    fun loadProductivityData(context: Context): List<ProductivityData> {
        val json: String?
        try {
            val inputStream = context.assets.open("productivity.json")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            return emptyList()
        }
        val listType = object : TypeToken<List<ProductivityData>>() {}.type
        return Gson().fromJson(json, listType)
    }

    fun loadEmployeeData(context: Context): List<EmployeeDataItem> {
        val json: String?
        try {
            val inputStream = context.assets.open("employee_list.json")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            return emptyList()
        }
        val listType = object : TypeToken<List<EmployeeDataItem>>() {}.type
        return Gson().fromJson(json, listType)
    }


    fun parseJson(jsonString: String): List<EmployeeDataItem> {
        return Gson().fromJson(jsonString, Array<EmployeeDataItem>::class.java).toList()
    }
}
