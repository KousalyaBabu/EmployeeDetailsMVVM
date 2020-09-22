package com.example.employeedetailsmvvm.repository

import EmployeeList
import retrofit2.Call
import retrofit2.http.GET

interface EmployeeEndPoint {
    @GET("v3/325512ea-9fd0-4aeb-9cf5-cfda5c96f7a0")
    fun getEmployee():Call<EmployeeList>
}