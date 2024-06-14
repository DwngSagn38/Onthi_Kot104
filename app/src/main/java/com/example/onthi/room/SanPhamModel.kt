package com.example.onthi.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SanPham")
data class SanPhamModel(
    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
    @ColumnInfo(name = "name") var name : String?,
    @ColumnInfo(name = "price") var price : Double?,
    @ColumnInfo(name = "description") var description : String?,
    @ColumnInfo(name = "status") var status : Boolean?,
    @ColumnInfo(name = "image") var image : String?,
)