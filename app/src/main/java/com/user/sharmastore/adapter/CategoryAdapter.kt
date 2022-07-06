package com.user.sharmastore.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.user.sharmastore.R
import com.user.sharmastore.activity.CategoryActivity
import com.user.sharmastore.databinding.ItmCategoryLayoutBinding
import com.user.sharmastore.model.CategoryModel

class CategoryAdapter(private var context : Context, private val list :ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class  CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var binding = ItmCategoryLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return  CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.itm_category_layout,parent,false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.tvItemCategory.text = list[position].cate
        Glide.with(context).load(list[position].img).into(holder.binding.CategoryItemImageView)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, CategoryActivity::class.java)
            intent.putExtra("cate",list[position].cate)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}