package com.example.toplaygamedemo.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * @author : Mingaleev D
 * @data : 10/01/2023
 */

@Database(entities = [GameEntity::class], version = 1, exportSchema = false)
@TypeConverters(GameTypeConverter::class)
abstract class GameDatabase : RoomDatabase() {

   abstract fun gameDao(): GameDao
}