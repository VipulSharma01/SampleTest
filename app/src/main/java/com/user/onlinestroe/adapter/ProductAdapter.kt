package com.user.onlinestroe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.user.onlinestroe.databinding.LayoutProductItemBinding
import com.user.onlinestroe.model.AddProductModel

class ProductAdapter (val context: Context,val list : ArrayList<AddProductModel>):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding : LayoutProductItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
            val binding = LayoutProductItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
                val data = list[position]
        Glide.with(context).load(data.productCoverImg).into(holder.binding.productImg)
        holder.binding.productName.text = data.productName
        holder.binding.category.text = data.productCategory
        holder.binding.MRP.text = data.productMrp
        holder.binding.SellingPrice.text = data.productSp

    }

    override fun getItemCount(): Int {
        return list.size
    }
}