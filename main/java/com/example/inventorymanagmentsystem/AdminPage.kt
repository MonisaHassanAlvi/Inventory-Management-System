package com.example.inventorymanagmentsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AdminPage : AppCompatActivity() {

    lateinit var radapter: RequestAdapter

    lateinit var list: ArrayList<Requests>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_page)

        var db = DatabaseHelper(this)
        /*db.insertData(Requests("Ahmad Riaz", 1, "Uniform"))
        db.insertData(Requests("Hamza Ali", 2, "Badge"))
        db.insertData(Requests("Kamran Baig", 2, "Boots"))
        db.insertData(Requests("Bilal Afzal", 1, "Notepad"))
        db.insertData(Requests("Sarmad Ali", 3, "Radar"))*/
        list = ArrayList<Requests>()

        var data = db.readData()
        for(i in 0 until data.size)
        {
            list.add(Requests(data.get(i).name,data.get(i).quantity,data.get(i).item))
        }

        if(list.isEmpty())
            Toast.makeText(applicationContext,"No more pending requests",Toast.LENGTH_SHORT).show()

        radapter =  RequestAdapter(list)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)

        recyclerView.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            // set the custom adapter to the RecyclerView
            adapter = radapter
        }

        radapter.onItemClick = {
            var index = 0
            for(l in 0 until list.size)
            {
                if(list[l].name == it)
                {
                    index = l
                }
            }
            var stock = list[index].item
            var quan = list[index].quantity

            var intent = Intent(applicationContext, RC::class.java)
            intent.putExtra("item",it)
            intent.putExtra("name",stock)
            intent.putExtra("quantity",quan)
            startActivity(intent)
        }

        radapter.onItemClick1 = {
            var index = 0
            for(l in 0 until list.size)
            {
                if(list[l].name == it)
                {
                    index = l
                }
            }
            list.removeAt(index)
            radapter.notifyDataSetChanged()
            db.deleteData(it)
            if(list.isEmpty())
                Toast.makeText(applicationContext,"No more pending requests",Toast.LENGTH_SHORT)
        }

    }


    fun btn_stock(view: View) {
        startActivity(Intent(applicationContext, Stock::class.java))
    }
}