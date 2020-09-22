package com.example.employeedetailsmvvm.repository

import EmployeeList
import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object EmployeeRepository {
    private const val TAG = "EmployeeRepository"
    private var instance: Retrofit? = null

    private fun getInstance(): Retrofit {
        if (null == instance) {
            instance = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://run.mocky.io/")
                .build()
        }
        return instance!!
    }

    fun getEmployeeList(): MutableLiveData<EmployeeList> {
        val employees = MutableLiveData<EmployeeList>()

        val employeeEndPoint = getInstance().create(EmployeeEndPoint::class.java)
        employeeEndPoint.getEmployee().enqueue(object : Callback<EmployeeList> {
            override fun onResponse(call: Call<EmployeeList>, response: Response<EmployeeList>) {
                employees.value = response.body()
                Log.d(TAG, "onResponse: ${employees.value}")
            }

            override fun onFailure(call: Call<EmployeeList>, t: Throwable) {
                t.printStackTrace()
                Log.d(TAG, "onFailure: ")
            }
        })
        Log.d(TAG, "onResponse: ${employees.value}")
        return employees
    }
}