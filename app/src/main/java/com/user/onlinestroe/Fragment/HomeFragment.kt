package com.user.onlinestroe.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.user.onlinestroe.R
import com.user.onlinestroe.adapter.CategoryAdapter
import com.user.onlinestroe.adapter.ProductAdapter
import com.user.onlinestroe.databinding.FragmentHomeBinding
import com.user.onlinestroe.model.AddProductModel
import com.user.onlinestroe.model.CategoryModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

     override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
         binding = FragmentHomeBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
         getCategories()
         getProducts()
         getSliderImage()
        return binding.root
    }

    private fun getSliderImage() {
        Firebase.firestore.collection("slider").document("item")
            .get().addOnSuccessListener {
                Glide.with(requireContext()).load(it.get("img")).into(binding.sliderImg)
            }
        Toast.makeText(context, "Slider is gain", Toast.LENGTH_LONG).show()
    }

    private fun getProducts() {
        val list = ArrayList<AddProductModel>()
        Firebase.firestore.collection("products")
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents){
                    val data = doc.toObject(AddProductModel::class.java)
                    list.add(data!!)
                }
                binding.ProductRecyclerView.adapter = ProductAdapter(requireContext(), list)
            }
    }

    private fun getCategories() {
        val list = ArrayList<CategoryModel>()
        Firebase.firestore.collection("categories")
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents){
                        val data = doc.toObject(CategoryModel::class.java)
                    list.add(data!!)
                }
                Toast.makeText(context, "data is geted", Toast.LENGTH_LONG).show()
                binding.categoryRecyclerView.adapter = CategoryAdapter(requireContext(),list)
            }
    }
}