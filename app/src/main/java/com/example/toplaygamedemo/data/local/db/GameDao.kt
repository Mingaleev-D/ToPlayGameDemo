package com.example.toplaygamedemo.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * @author : Mingaleev D
 * @data : 10/01/2023
 */

@Dao
interface GameDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertGame(gameEntity: GameEntity)

   @Query("SELECT * FROM game_table ORDER BY id ASC")
   fun readGame():Flow<List<GameEntity>>
}