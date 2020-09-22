package com.example.employeedetailsmvvm.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.employeedetailsmvvm.R
import com.example.employeedetailsmvvm.data.Employee

class EmployeeAdapter(private val context: Context) :
    RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {
    var employees: ArrayList<Employee>? = null

    companion object {
        private const val TAG = "EmployeeAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.employee_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = employees?.get(position)?.id.toString()
        holder.name.text = employees?.get(position)?.employee_name
        holder.salary.text = employees?.get(position)?.employee_salary.toString()
        holder.itemView.setOnClickListener {
            Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: $employees")
        if (null != employees)
            return employees?.size!!
        return 0
    }

    fun deleteItem(adapterPosition: Int) {
        employees?.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id: TextView = view.findViewById(R.id.empId)
        val name: TextView = view.findViewById(R.id.empName)
        val salary: TextView = view.findViewById(R.id.empSalary)
    }
}