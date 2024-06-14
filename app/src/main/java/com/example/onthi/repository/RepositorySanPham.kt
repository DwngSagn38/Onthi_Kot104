package com.example.onthi.repository

import androidx.room.Update
import com.example.onthi.room.DBHelper
import com.example.onthi.room.SanPhamModel

class RepositorySanPham(val dbHelper: DBHelper) {
    suspend fun AddSanPhamToRoom(sanPhamModel: SanPhamModel){
        dbHelper.sanPhamDao().addSanPham(sanPhamModel)
    }

    fun getAll() = dbHelper.sanPhamDao().getAll()

    suspend fun DeleteSanPhamFromRoom(sanPhamModel: SanPhamModel){
        dbHelper.sanPhamDao().deleteSanPham(sanPhamModel)
    }

    suspend fun UpdateSanPhamFromRoom(sanPhamModel: SanPhamModel){
        dbHelper.sanPhamDao().updateSanPham(sanPhamModel)
    }

    fun getTang() = dbHelper.sanPhamDao().sapXepGiaTang()
    fun getGiam() = dbHelper.sanPhamDao().sapXepGiaGiam()

//    fun timKiem(key: String) = dbHelper.sanPhamDao().timKiemTheoTuKhoa(key)
}