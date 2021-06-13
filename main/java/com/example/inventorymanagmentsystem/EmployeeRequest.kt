package com.example.inventorymanagmentsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.lang.Double

class EmployeeRequest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_request)
    }
    fun validateUserInfo(): Boolean {

        var nameEd = findViewById<EditText>(R.id.name)
        var itemEd = findViewById<EditText>(R.id.item)
        var quanEd = findViewById<EditText>(R.id.quantity)

        var name = findViewById<EditText>(R.id.name).text.toString()
        var item = findViewById<EditText>(R.id.item).text.toString()
        //var quan = findViewById<EditText>(R.id.quantity).text.toString()

        if(name == "")
        {
            nameEd.error = "Invalid Name"
            return false
        }
        if(item == "")
        {
            itemEd.error = "Invalid Item"
            return false
        }
       /* var numeric = true

        try {
            val num = Double.parseDouble(quan)
        } catch (e: NumberFormatException) {
            numeric = false
        }

        if(quan == "" || !numeric)
        {
            quanEd.error = "Invalid Number"
            return false
        }*/
        return true
    }

    fun btn_toast(view: View) {
        if(validateUserInfo())
            Toast.makeText(applicationContext,"Request Submitted",Toast.LENGTH_SHORT).show()
    }
}