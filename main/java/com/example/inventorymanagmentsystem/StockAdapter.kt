package com.example.inventorymanagmentsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StockAdapter(private val SList: List<Stocks>) : RecyclerView.Adapter<StockAdapter.ViewHolder>() {

    var onItemClick: ((String) -> Unit)? = null
    var onItemClick1: ((String) -> Unit)? = null

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val t1: TextView = itemView.findViewById(R.id.textView11)
        val t2: TextView = itemView.findViewById(R.id.textView12)

        val b1: Button = itemView.findViewById(R.id.minus)
        val b2: Button = itemView.findViewById(R.id.plus)

        init {
            b1.setOnClickListener {
                onItemClick?.invoke(SList[adapterPosition].item)
            }
            b2.setOnClickListener {
                onItemClick1?.invoke(SList[adapterPosition].item)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stock,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = SList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder) {
            is ViewHolder -> {
                holder.t1.setText(SList[position].item)
                holder.t2.setText(SList[position].quantity.toString())

            }
        }
    }

}

