package com.example.ecommerceapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import coil.load
import com.example.ecommerceapp.databinding.ActivityProductDetailBinding

class ProductDetail : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)

       var description: String? = intent.getStringExtra("DESCRIPTION")
        var id: Int? = intent.getIntExtra("ID",0)
        var image: String? = intent.getStringExtra("IMAGE")
        var price: Double? = intent.getDoubleExtra("PRICE", 0.0)
        var title: String? = intent.getStringExtra("TITLE")

        binding.apply {
            productImage.load(image)
            productTitle.text = title
            productPrice.text = "$${price.toString()}"
            productDesc.text = description

        }

    }
}