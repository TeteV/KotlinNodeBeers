package com.example.bicycles

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.bicycles.models.Bicycle

class BicycleAdapter (bicycleList: ArrayList<Bicycle>) : RecyclerView.Adapter<BicycleAdapter.viewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.bicycle_list_row, parent, false)
        return viewHolder(v)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bindview(bicycleList[position])
    }


    override fun getItemCount(): Int {
        return  bicycleList.size;
    }

    class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun bindview(b:Bicycle){
        val text_brand: TextView= itemView.findViewById(R.id.textViewBrand)
        val text_model: TextView= itemView.findViewById(R.id.textViewModel)
        text_brand.text = b.brand
        text_model.text = b.model
        //itemView.textViewBrand = b.brand;
    }
    }
}