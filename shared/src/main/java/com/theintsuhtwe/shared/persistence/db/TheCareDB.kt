package com.theintsuhtwe.shared.persistence.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.theintsuhtwe.shared.data.vos.DoctorVO


//
////@Database(entities = [ CategoryVO::class, DoctorVO::class], version = 2, exportSchema = false)
//@Database(entities = [ DoctorVO::class], version = 2, exportSchema = false)
//abstract class TheCareDB : RoomDatabase() {
//    companion object {
//        val DB_NAME = "TheCareApp.DB"
//        var dbInstance: TheCareDB? = null
//
//        fun getDBInstance(context: Context): TheCareDB {
//            when (dbInstance) {
//                null -> {
//                    dbInstance = Room.databaseBuilder(context, TheCareDB::class.java, DB_NAME)
//                        .allowMainThreadQueries()
//                        .fallbackToDestructiveMigration()
//                        .build()
//                }
//            }
//
//            val i = dbInstance
//            return i!!
//        }
//    }
//
//   // abstract fun categoryDao(): CategoryDao
//    abstract fun doctorDao(): DoctorVO
//
//}