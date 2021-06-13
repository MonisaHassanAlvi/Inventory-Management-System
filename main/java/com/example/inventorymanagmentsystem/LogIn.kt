package com.example.inventorymanagmentsystem

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LogIn : AppCompatActivity() {
    lateinit var list: ArrayList<Employee>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        list = ArrayList<Employee>()
        list.add(Employee("l174098@gmail.com","123456"))
        list.add(Employee("l174122@gmail.com","123123"))
        list.add(Employee("l174327@gmail.com","456456"))
    }

    fun ValidateInfo() : Boolean        //Admin LogIn
    {
        var email = findViewById<EditText>(R.id.email)
        var emailText = email.text.toString()

        if(emailText != "aiza.nadeem2001@gmail.com") {
            email.error = "Invalid Email"
            return false
        }
        else
            email.error = null

        var password = findViewById<EditText>(R.id.password)
        var passText = password.text.toString()

        if(passText != "03078144696")
        {
            password.error = "Invalid Password"
            return false
        }
        else
            email.error = null
        return true
    }

    fun ValidatEmployee() : Boolean        //Employee LogIn
    {
        var email = findViewById<EditText>(R.id.email)
        var emailText = email.text.toString()

        var flag1 = true
        var flag2 = true

        for(i in 0 until list.size ) {
            flag1 = true
            flag2 = true

            if (emailText != list[i].Email) {
                email.error = "Invalid Email"
                flag1 = false
            } else
                email.error = null

            var password = findViewById<EditText>(R.id.password)
            var passText = password.text.toString()

            if (passText != list[i].password) {
                password.error = "Invalid Password"
                flag2 = false
            } else
                password.error = null
            if(flag1  &&  flag2)
                return true
        }
        return false
    }

    fun btn_req(view: View) {
        if(ValidateInfo())
            startActivity(Intent(applicationContext, AdminPage::class.java))
        if(ValidatEmployee())
            startActivity(Intent(applicationContext, EmployeeRequest::class.java))
    }

}