package com.user.sharmastore.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.user.sharmastore.MainActivity
import com.user.sharmastore.databinding.ActivityProductDetailsBinding
import com.user.sharmastore.roomdb.AppDatabase
import com.user.sharmastore.roomdb.ProductDao
import com.user.sharmastore.roomdb.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        getProductDetail(intent.getStringExtra("id"))
        setContentView(binding.root)

    }

    private fun getProductDetail(proId : String?) {
            Firebase.firestore.collection("products").document(proId!!).get().addOnSuccessListener {

                val list = it.get("productImages") as ArrayList<String>
                val name = it.getString("productName")
                val productSp = it.getString("productSp")
                val productDesc =it.getString("productDescription")
                binding.textview7.text = name
                binding.textView8.text = productSp
                binding.textview9.text = productDesc

                val sliderlist = ArrayList<SlideModel>()
                for (data in list){
                    sliderlist.add(SlideModel(data , ScaleTypes.CENTER_CROP))
                }

              cartAction(proId,name,productSp,it.getString("productCoverImg"))

                binding.imageSlider.setImageList(sliderlist)
            }
                .addOnFailureListener {
                    Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
                }
    }

    private fun cartAction(proId: String, name: String?, productSp: String?, CoverImg: String?) {

        val productDao = AppDatabase.getInstance(this).productDao()

        if (productDao.isExit(proId)!=null){
                binding.textView3.text ="Go To Cart"
        }
        else{
            binding.textView3.text= "Add To Cart"
        }
        binding.textView3.setOnClickListener {
            if (productDao.isExit(proId) != null){
                    openCart()
            }
            else{
                    addToCart(productDao,proId,name,productSp,CoverImg)
            }
        }
    }

    private fun addToCart(productDao: ProductDao, proId: String, name: String?, productSp: String?, coverImg: String?) {
        val data = ProductModel(proId,name,productSp,coverImg)
        lifecycleScope.launch(Dispatchers.IO){
            productDao.insertProduct(data)
            binding.textView3.text ="Go TO Cart"

        }
    }

    private fun openCart() {
        val preferences = this.getSharedPreferences("info", MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean("isCart",true)
        editor.apply()

        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

}