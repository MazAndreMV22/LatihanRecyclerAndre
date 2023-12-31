package com.example.latihanrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CoffeeListAdapter(private val listCoffee: ArrayList<Coffee>) : RecyclerView.Adapter<CoffeeListAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (nama, deskripsi, image) = listCoffee[position]
        holder.apply {
            imgItem.setImageResource(image)
            tvNama.text = nama
            tvDeskripsi.text = deskripsi
            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(listCoffee[holder.adapterPosition])
            }
        }
    }

    override fun getItemCount(): Int = listCoffee.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgItem: ImageView = itemView.findViewById(R.id.img_item)
        var tvNama: TextView = itemView.findViewById(R.id.tv_item_nama)
        var tvDeskripsi: TextView = itemView.findViewById(R.id.tv_item_deskripsi)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Coffee)
    }

}