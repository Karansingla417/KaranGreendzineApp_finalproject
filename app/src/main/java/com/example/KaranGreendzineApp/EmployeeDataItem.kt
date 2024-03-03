package com.example.KaranGreendzineApp

import com.google.gson.annotations.SerializedName

data class EmployeeDataItem(
    @SerializedName("EMP ID") val empId: Int,
    val date: String,
    val name: String,
    val role: String
)