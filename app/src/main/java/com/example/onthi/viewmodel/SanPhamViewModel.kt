package com.example.onthi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onthi.repository.RepositorySanPham
import com.example.onthi.room.SanPhamModel
import kotlinx.coroutines.launch

class SanPhamViewModel(val repository: RepositorySanPham) : ViewModel() {
    private val _timKiemSanPhams = MutableLiveData<List<SanPhamModel>>()
    val timKiemSanPhams: LiveData<List<SanPhamModel>> get() = _timKiemSanPhams

    fun addSanPham( name : String, price : String, description : String, status : String) : String {
        if (name.isEmpty() || price.isEmpty() || description.isEmpty() || status.isEmpty()){
            return "Khong duoc de trong du lieu"
        }
        if (!isDouble(price)){
            return "Gia san pham chua dung"
        }
        val sanPham = SanPhamModel(0,name,price.toDouble(),description,status.toBoolean())
        viewModelScope.launch {
            repository.AddSanPhamToRoom(sanPham)
        }
        return "Them thanh cong"
    }

    val sanPhams = repository.getAll()

    val getTang = repository.getTang()
    val getGiam = repository.getGiam()

    fun deleteSanPham(sanPhamModel: SanPhamModel) : String {
        viewModelScope.launch {
            repository.DeleteSanPhamFromRoom(sanPhamModel)
        }
        return "Xoa thanh cong"
    }

    fun updateSanPham(sanPhamModel: SanPhamModel){
        viewModelScope.launch {
            repository.UpdateSanPhamFromRoom(sanPhamModel)
        }
    }

    fun timKiem(key: String) {
        viewModelScope.launch {
            repository.timKiem(key).collect { result ->
                _timKiemSanPhams.value = result
            }
        }
    }
}

fun isDouble(value: String): Boolean {
    return try {
        value.toDouble()
        true
    } catch (e: NumberFormatException) {
        false
    }
}