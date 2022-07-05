package com.user.onlinestroe.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.user.onlinestroe.R
import com.user.onlinestroe.databinding.LayoutCategoryItemBinding
import com.user.onlinestroe.model.CategoryModel

class CategoryAdapter(private var context : Context, private val list :ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class  CategoryViewHolder(view: View) :RecyclerView.ViewHolder(view) {

            var binding = LayoutCategoryItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return  CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_category_item,parent,false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.textView.text = list[position].cate
        Glide.with(context).load(list[position].img).into(holder.binding.imageView)

    }

    override fun getItemCount(): Int {
        return list.size
    }
}