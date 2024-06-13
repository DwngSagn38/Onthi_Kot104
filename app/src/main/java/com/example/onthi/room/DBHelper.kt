package com.example.onthi.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SanPhamModel::class], version = 1)
abstract class DBHelper : RoomDatabase(){
    abstract fun sanPhamDao() : SanPhamDao

    companion object {
        @Volatile
        private var INTANCE : DBHelper? = null

        fun getIntance(context: Context): DBHelper{
            synchronized(this){
                var intance = INTANCE
                if (intance == null) {
                    intance = Room.databaseBuilder(
                        context.applicationContext,
                        DBHelper::class.java,
                        "my_database"
                    ).build()
                }
                return intance
            }
        }
    }
}