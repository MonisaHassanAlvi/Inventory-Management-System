package com.example.inventorymanagmentsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import java.lang.Double


class Inventory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)
    }

    fun validateUserInfo(): Boolean {

        var REd = findViewById<EditText>(R.id.RemainingQuantity)
        var R = findViewById<EditText>(R.id.RemainingQuantity).text.toString()

        var numeric = true

        try {
            val num = Double.parseDouble(R)
        } catch (e: NumberFormatException) {
            numeric = false
        }

        if(R == "" || !numeric)
        {
            REd.error = "Invalid Quantity"
            return false
        }
        return true
    }

    fun btn_success(view: View) {

        if (validateUserInfo()) {

            val RQuantity = findViewById<EditText>(R.id.RemainingQuantity).text.toString()
            val Rname = findViewById<EditText>(R.id.ReceiverName).text.toString()

            var db = DatabaseHelper(this)
            db.insertData3(InventoryValues(RQuantity.toInt(), Rname))

            Toast.makeText(applicationContext, "Request Approved", Toast.LENGTH_SHORT).show()


            val bundle = intent.extras
            val message = bundle!!.getString("item")
            val message1 = bundle!!.getString("name")
            val message2 = bundle!!.getInt("quantity")

            Log.d("msg", message.toString())
            Log.d("msg1", message1.toString())
            Log.d("msg2", message2.toString())

            message?.let { db.deleteData(it) }

            var data = db.readData1()
            var number = 0
            for (i in 0 until data.size) {
                if (data[i].item == message1) {
                    number = data[i].quantity
                }
            }

            number -= message2

            if (message1 != null) {
                db.updateData1(message1, number)
            }

            startActivity(Intent(applicationContext, AdminPage::class.java))
        }
    }
}