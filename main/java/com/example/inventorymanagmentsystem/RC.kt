package com.example.inventorymanagmentsystem

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import java.lang.Double.parseDouble

class RC : AppCompatActivity() {

    var id = ""
    var Iname = ""
    var quan = ""
    var branch = ""
    var Rname = ""
    var date = ""
    var day = 0
    var month: Int = 0
    var year: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_r_c)

        val button = findViewById<EditText>(R.id.date)
        button.setOnClickListener(View.OnClickListener {
            val datePickerDialog = DatePickerDialog(
                    this@RC, OnDateSetListener { view, year, month, day ->
                var month = month
                month = month + 1
                val date = "$day/$month/$year"
                button.setText(date)
            }, year, month, day)
            datePickerDialog.show()
        })

    }

    fun validateUserInfo(): Boolean {

        var idEd = findViewById<EditText>(R.id.id)
        var InameEd = findViewById<EditText>(R.id.iname)
        var quanEd = findViewById<EditText>(R.id.quan)
        var branchEd = findViewById<EditText>(R.id.branch)
        var RnameEd = findViewById<EditText>(R.id.rname)
        var dateEd = findViewById<EditText>(R.id.date)

        id = findViewById<EditText>(R.id.id).text.toString()
        Iname = findViewById<EditText>(R.id.iname).text.toString()
        quan = findViewById<EditText>(R.id.quan).text.toString()
        branch = findViewById<EditText>(R.id.branch).text.toString()
        Rname = findViewById<EditText>(R.id.rname).text.toString()
        date = findViewById<EditText>(R.id.date).text.toString()

        var numeric = true

        try {
            val num = parseDouble(id)
        } catch (e: NumberFormatException) {
            numeric = false
        }

        if(id == "" || !numeric)
        {
            idEd.error = "Invalid Item Id"
            return false
        }
        if(Iname == "")
        {
            InameEd.error = "Invalid Item Name"
            return false
        }
        numeric = true

        try {
            val num = parseDouble(quan)
        } catch (e: NumberFormatException) {
            numeric = false
        }

        if(quan == "" || !numeric)
        {
            quanEd.error = "Invalid Number"
            return false
        }
        if(branch == "")
        {
            branchEd.error = "Invalid Branch Name"
            return false
        }
        if(Rname == "")
        {
            RnameEd.error = "Invalid Receiver Name"
            return false
        }
        if(date == "")
        {
            dateEd.error = "Invalid Date"
            return false
        }
        return true
    }

    fun btn_inv(view: View) {

        if(validateUserInfo()) {

            id = findViewById<EditText>(R.id.id).text.toString()
            Iname = findViewById<EditText>(R.id.iname).text.toString()
            quan = findViewById<EditText>(R.id.quan).text.toString()
            branch = findViewById<EditText>(R.id.branch).text.toString()
            Rname = findViewById<EditText>(R.id.rname).text.toString()
            date = findViewById<EditText>(R.id.date).text.toString()

            var db = DatabaseHelper(this)
            db.insertData2(RCValues(id.toInt(), Iname, quan.toInt(), branch, Rname, date))

            val bundle = intent.extras
            val message = bundle!!.getString("item")
            val message1 = bundle!!.getString("name")
            val message2 = bundle!!.getInt("quantity")

            Log.d("msg", message.toString())
            Log.d("msg1", message1.toString())
            Log.d("msg2", message2.toString())

            var intent = Intent(applicationContext, Inventory::class.java)
            intent.putExtra("item", message)
            intent.putExtra("name", message1)
            intent.putExtra("quantity", message2)

            startActivity(intent)
        }

    }
}