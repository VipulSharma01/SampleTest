package com.user.sharmastore.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.user.sharmastore.activity.ProductDetailsActivity
import com.user.sharmastore.databinding.ItemProductLayoutBinding
import com.user.sharmastore.model.AddProductModel

class ProductAdapter (private val context: Context, private val list : ArrayList<AddProductModel>):
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding : ItemProductLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val data = list[position]
        Glide.with(context).load(data.productCoverImg).into(holder.binding.productImg)

        holder.binding.productName.text = data.productName
        holder.binding.category.text = data.productCategory
        holder.binding.MRP.text = data.productMrp
        holder.binding.SellingPrice.text = data.productSp

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("id",list[position].productId)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}