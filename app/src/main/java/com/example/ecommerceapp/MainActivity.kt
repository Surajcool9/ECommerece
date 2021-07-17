package com.example.ecommerceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.adapter.ProductListAdapter
import com.example.ecommerceapp.databinding.ActivityMainBinding
import com.example.ecommerceapp.model.ProductListModel
import com.example.ecommerceapp.utility.UtilityKs
import com.example.ecommerceapp.viewmodel.ProductListViewModel

class MainActivity : AppCompatActivity(), ProductListAdapter.ClickOnCard {

    private lateinit var binding:ActivityMainBinding
    private lateinit var productList : List<ProductListModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val viewModel = ViewModelProvider(this).get(ProductListViewModel::class.java)

        binding.productGrid.visibility = View.GONE
        UtilityKs.startShimmer(binding.shimmerView)
        viewModel.getProductList().observe(this, Observer<List<ProductListModel>> {
            UtilityKs.stopShimmer(binding.shimmerView)
            binding.productGrid.visibility = View.VISIBLE
            if(it != null) {
                productList = it
                setAdapter(productList)
            } else {
                Toast.makeText(this, "NO DATA FOUND", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.apiCall()
    }

    private fun setAdapter(productList: List<ProductListModel>) {

        val layoutManager = GridLayoutManager(this,2,RecyclerView.VERTICAL,false)
        binding.productGrid.layoutManager = layoutManager
        val productListAdapter = ProductListAdapter(this)
        binding.productGrid.adapter = productListAdapter
        productListAdapter.setList(productList)
    }

    override fun onPause() {
        super.onPause()
        UtilityKs.stopShimmer(binding.shimmerView)
    }

    override fun onclick(
        description: String,
        id: Int,
        image: String,
        price: Double,
        title: String
    ) {
        val intent = Intent(this, ProductDetail::class.java).apply {
            putExtra("ID", id)
            putExtra("PRICE",price)
            putExtra("IMAGE", image.toString())
            putExtra("TITLE", title.toString())
            putExtra("DESCRIPTION", description.toString())
        }
        startActivity(intent)
    }
}