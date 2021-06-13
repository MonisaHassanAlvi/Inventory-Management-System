package com.example.inventorymanagmentsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RequestAdapter(private val RList: List<Requests>) : RecyclerView.Adapter<RequestAdapter.ViewHolder>() {

    var onItemClick: ((String) -> Unit)? = null
    var onItemClick1: ((String) -> Unit)? = null
    //private var listener: ((item: Requests) -> Unit)? = null

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val t1: TextView = itemView.findViewById(R.id.textView11)
        val t2: TextView = itemView.findViewById(R.id.textView12)
        val t3: TextView = itemView.findViewById(R.id.textView13)

        val b1: Button = itemView.findViewById(R.id.button1)
        val b2: Button = itemView.findViewById(R.id.button2)

        init {
            b1.setOnClickListener {
                onItemClick?.invoke(RList[adapterPosition].name)
            }
            b2.setOnClickListener {
                onItemClick1?.invoke(RList[adapterPosition].name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.requests,parent,false)
        return ViewHolder(view)
    }

    /*fun setOnItemClickListener(listener: (item: Requests) -> Unit) {
        this.listener = listener
    }*/

    override fun getItemCount(): Int = RList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder) {
            is ViewHolder -> {
                holder.t1.setText(RList[position].name)
                holder.t2.setText(RList[position].quantity.toString())
                holder.t3.setText(RList[position].item)
            }
        }
    }

}

