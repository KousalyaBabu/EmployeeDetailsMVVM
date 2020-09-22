package com.example.employeedetailsmvvm.viewModel

import EmployeeList
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employeedetailsmvvm.repository.EmployeeRepository

class EmployeeViewModel:ViewModel() {
    companion object{
        private const val TAG = "EmployeeViewModel"
    }
    private var employeeList:MutableLiveData<EmployeeList>?=null

    fun getEmployeeList():LiveData<EmployeeList>{
        employeeList=employeeList?:EmployeeRepository.getEmployeeList()
        Log.d(TAG, "getEmployeeist: ${EmployeeRepository.getEmployeeList().value}")
        return employeeList!!
    }
}