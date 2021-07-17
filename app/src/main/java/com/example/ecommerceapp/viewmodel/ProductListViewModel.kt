package com.example.ecommerceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.model.ProductListModel

import com.example.ecommerceapp.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductListViewModel : ViewModel() {

    var productListLiveData: MutableLiveData<List<ProductListModel>> = MutableLiveData<List<ProductListModel>>()

    fun getProductList():MutableLiveData<List<ProductListModel>> {
        return productListLiveData
    }

    fun apiCall(){
        viewModelScope.launch(Dispatchers.IO) {
           val response = RetrofitInstance.apiService.getProductList()
            productListLiveData.postValue(response)
        }
    }
}