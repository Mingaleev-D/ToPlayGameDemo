package com.example.toplaygamedemo.data.datasource

import com.example.toplaygamedemo.data.local.db.GameDao
import com.example.toplaygamedemo.data.local.db.GameEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 12/01/2023
 */

class LocalDataSource @Inject constructor(
   private val gameDao: GameDao
) {

   suspend fun insertGames(gameEntity: GameEntity) {
      return gameDao.insertGame(gameEntity)
   }

   fun readDatabase(): Flow<List<GameEntity>> {
      return gameDao.readGame()
   }
}