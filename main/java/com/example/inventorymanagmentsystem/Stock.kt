package com.example.inventorymanagmentsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Stock : AppCompatActivity() {

    lateinit var radapter: StockAdapter

    lateinit var list: ArrayList<Stocks>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock)

        var db = DatabaseHelper(this)

        /*db.insertData1(Stocks("Uniform",110))
        db.insertData1(Stocks("Badge",200))
        db.insertData1(Stocks("Boots",250))
        db.insertData1(Stocks("Notepad",500))
        db.insertData1(Stocks("Radar",60))
        db.insertData1(Stocks("Pen",200))
        db.insertData1(Stocks("Speed Gun",80))*/

        list = ArrayList<Stocks>()

        var data = db.readData1()
        for(i in 0 until data.size)
        {
            list.add(Stocks(data.get(i).item,data.get(i).quantity))
        }

        radapter =  StockAdapter(list)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview1)

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
                if(list[l].item == it)
                {
                    index = l
                }
            }
            list.get(index).quantity -= 1
            val q = list.get(index).quantity
            radapter.notifyDataSetChanged()
            db.updateData1(it,q)
        }
        radapter.onItemClick1 = {
            var index = 0
            for(l in 0 until list.size)
            {
                if(list[l].item == it)
                {
                    index = l
                }
            }
            list.get(index).quantity += 1
            val q = list.get(index).quantity
            radapter.notifyDataSetChanged()
            db.updateData1(it,q)
        }
    }


    fun btn_request(view: View) {
        startActivity(Intent(applicationContext, AdminPage::class.java))
    }
}