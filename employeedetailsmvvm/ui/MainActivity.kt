package com.example.employeedetailsmvvm.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.employeedetailsmvvm.R
import com.example.employeedetailsmvvm.data.Employee
import com.example.employeedetailsmvvm.viewModel.EmployeeViewModel

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private var empRecView: RecyclerView? = null
    private var adapter: EmployeeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecView()

        val employeeViewModel = ViewModelProvider(this)[EmployeeViewModel::class.java]
        employeeViewModel.getEmployeeList().observe(this, Observer {
            adapter?.employees = it?.data as ArrayList<Employee>?
            adapter?.notifyDataSetChanged()
            Log.d(TAG, "onCreate: $it")
        })
    }

    private fun initRecView() {
        empRecView = findViewById(R.id.empRecView)
        empRecView?.layoutManager = LinearLayoutManager(this)
        adapter = EmployeeAdapter(this)
        empRecView?.adapter = adapter
        val decoration=DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        empRecView?.addItemDecoration(decoration)
        val itemTouchHelper = ItemTouchHelper(SimpleTouchCallBack(adapter!!))
        itemTouchHelper.attachToRecyclerView(empRecView)
    }
}