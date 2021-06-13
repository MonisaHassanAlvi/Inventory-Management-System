package com.example.inventorymanagmentsystem

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

val DATABASE_NAME = "MyDB"

val TABLE_NAME = "Requests"
val COL_NAME = "name"
val COL_QUANTITY = "quantity"
val COL_ITEM = "item"

val TABLE_NAME1 = "Stocks"
val COL_QUANTITY1 = "quantity"
val COL_ITEM1 = "item"

val TABLE_NAME2 = "RC"
val COL_ITEM_ID = "id"
val COL_ITEM_NAME = "name"
val COL_ITEM_QUANTITY = "quantity"
val COL_ITEM_BRANCH = "branch"
val COL_ITEM_RECEIVER = "receiver"
val COL_DATE = "date"

val TABLE_NAME3 = "Inventory"
val COL_REMAINING = "remaining"
val COL_RECEIVER = "receiver"

class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1)
{
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" + COL_NAME +
                " VARCHAR(256)," + COL_QUANTITY + " INTEGER," + COL_ITEM + " VARCHAR(256))"

        val createTableS = "CREATE TABLE " + TABLE_NAME1 + " (" +
                COL_QUANTITY1 + " INTEGER," + COL_ITEM1 + " VARCHAR(256))"

        val createTableRC = "CREATE TABLE " + TABLE_NAME2 + " (" +
                                    COL_ITEM_ID + " INTEGER," + COL_ITEM_NAME + " VARCHAR(256)," +
                                    COL_ITEM_QUANTITY + " INTEGER," + COL_ITEM_BRANCH + " VARCHAR(256)," +
                                    COL_ITEM_RECEIVER + " VARCHAR(256)," + COL_DATE + " VARCHAR(256))"

        val createTableI = "CREATE TABLE " + TABLE_NAME3 + " (" +
                                    COL_REMAINING + " INTEGER," +
                                    COL_RECEIVER + " VARCHAR(256))"

                db?.execSQL(createTable)
                db?.execSQL(createTableS)
                db?.execSQL(createTableRC)
                db?.execSQL(createTableI)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertData(request: Requests)
    {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME,request.name)
        cv.put(COL_QUANTITY,request.quantity)
        cv.put(COL_ITEM,request.item)
        var result = db.insert(TABLE_NAME,null,cv)
        //if(result == -1.toLong());
        //Toast.makeText(this,"Failed",Toast.LENGTH_SHORT)
    }

    fun insertData1(stock: Stocks)
    {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_QUANTITY1,stock.quantity)
        cv.put(COL_ITEM1,stock.item)
        var result = db.insert(TABLE_NAME1,null,cv)
        //if(result == -1.toLong());
        //Toast.makeText(this,"Failed",Toast.LENGTH_SHORT)
    }

    fun insertData2(r: RCValues)
    {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_ITEM_ID,r.ItemId)
        cv.put(COL_ITEM_NAME,r.ItemName)
        cv.put(COL_ITEM_QUANTITY,r.Quantity)
        cv.put(COL_ITEM_BRANCH,r.branch)
        cv.put(COL_ITEM_RECEIVER,r.ReceiveName)
        cv.put(COL_DATE,r.date)

        var result = db.insert(TABLE_NAME2,null,cv)
        //if(result == -1.toLong());
        //Toast.makeText(this,"Failed",Toast.LENGTH_SHORT)
    }

    fun insertData3(inv: InventoryValues)
    {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_REMAINING,inv.RItems)
        cv.put(COL_RECEIVER,inv.RName)
        var result = db.insert(TABLE_NAME3,null,cv)
        //if(result == -1.toLong());
        //Toast.makeText(this,"Failed",Toast.LENGTH_SHORT)
    }


    fun readData(): MutableList<Requests>
    {
        var list: MutableList<Requests> = ArrayList ()
        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query, null)

        Log.d("Data", result.toString())

        if(result.moveToFirst())
        {
            do {
                var request = Requests(result.getString(result.getColumnIndex(COL_NAME)).toString(),
                result.getString(result.getColumnIndex(COL_QUANTITY)).toInt(),
                result.getString(result.getColumnIndex(COL_ITEM)).toString())
                list.add(request)
            }while (result.moveToNext())

        }

        result.close()
        db.close()

        return list
    }

    fun readData1(): MutableList<Stocks>
    {
        var list: MutableList<Stocks> = ArrayList ()
        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME1
        val result = db.rawQuery(query, null)

        if(result.moveToFirst())
        {
            do {
                var stock = Stocks(
                        result.getString(result.getColumnIndex(COL_ITEM1)).toString(),
                        result.getString(result.getColumnIndex(COL_QUANTITY1)).toInt())
                list.add(stock)
            }while (result.moveToNext())

        }

        result.close()
        db.close()

        return list
    }



    fun deleteData(name: String)
    {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, COL_NAME+"=?", arrayOf(name))
        db.close()
    }

    fun updateData1(item: String, quan: Int)
    {
        val db = this.writableDatabase
        val query = "Select * from " + TABLE_NAME1
        val result = db.rawQuery(query, null)

        if(result.moveToFirst())
        {
            do {
                var cv = ContentValues()
                cv.put(COL_QUANTITY1,quan)
                db.update(TABLE_NAME1,cv, COL_ITEM1 + "=?", arrayOf(item))
            }while (result.moveToNext())
        }

        result.close()
        db.close()
    }
}