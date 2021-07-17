package com.example.ecommerceapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ProductListViewBinding
import com.example.ecommerceapp.model.ProductListModel

class ProductListAdapter(val clickOnCard: ClickOnCard) :
    RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    lateinit var products: List<ProductListModel>
    private lateinit var context: Context

    companion object {
        var clickOnCard : ClickOnCard? = null
    }

    class ProductViewHolder( val binding: ProductListViewBinding) : RecyclerView.ViewHolder(binding.root)

    interface ClickOnCard {
        fun onclick(description: String,
                    id: Int,
                    image: String,
                    price: Double,
                    title: String)
    }

    fun setList(products: List<ProductListModel>) {
        this.products = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        context = parent.context
        return ProductViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.product_list_view,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.apply {
            productImage.load(products[position].image)

            productTitle.text = products[position].title
            var product_price:String = "$${products[position].price}"
            productPrice.text = product_price

            productCard.setOnClickListener {
                clickOnCard.onclick(products[position].description,
                    products[position].id,
                    products[position].image,
                    products[position].price,
                    products[position].title)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (products.size > 0)
            products.size
        else 0
    }

}