package com.example.toplaygamedemo.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.toplaygamedemo.common.Constants.DATABASE_TABLE
import com.example.toplaygamedemo.data.dto.GameListResponseDto

/**
 * @author : Mingaleev D
 * @data : 10/01/2023
 */

@Entity(tableName = DATABASE_TABLE)
class GameEntity(
   var gameEntity: GameListResponseDto
) {
   @PrimaryKey(autoGenerate = true)
   var id: Int = 0
}