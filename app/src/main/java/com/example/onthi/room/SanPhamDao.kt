package com.example.onthi.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SanPhamDao {
    @Insert
    suspend fun addSanPham(sanPhamModel: SanPhamModel)

    @Query("SELECT * FROM SanPham")
    fun getAll() : Flow<List<SanPhamModel>>

    @Query("SELECT * FROM SanPham ORDER BY price ASC")
    fun sapXepGiaTang() : Flow<List<SanPhamModel>>

    @Query("SELECT * FROM SanPham ORDER BY price DESC")
    fun sapXepGiaGiam() : Flow<List<SanPhamModel>>

    @Query("SELECT * FROM SanPham WHERE name LIKE '%' || :keyword || '%'")
    fun timKiemTheoTuKhoa(keyword: String): Flow<List<SanPhamModel>>

    @Delete
    suspend fun deleteSanPham(sanPhamModel: SanPhamModel)

    @Update
    suspend fun updateSanPham(sanPhamModel: SanPhamModel)
}