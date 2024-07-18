package com.example.replicaroom.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.replicaroom.entity.City

@Database(entities = [City::class],version = 1)
abstract class AppDataBase:RoomDatabase() {
    abstract fun cityDao():CityDao
}