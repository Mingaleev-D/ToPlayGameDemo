package com.example.toplaygamedemo.data.local.db

import androidx.room.TypeConverter
import com.example.toplaygamedemo.data.dto.GameListResponseDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author : Mingaleev D
 * @data : 10/01/2023
 */

class GameTypeConverter {

   var gson = Gson()

   @TypeConverter
   fun gameToString(game: GameListResponseDto): String {
      return gson.toJson(game)
   }


   @TypeConverter
   fun stringToGame(data: String): GameListResponseDto {
      val listType = object : TypeToken<GameListResponseDto>() {}.type
      return gson.fromJson(data, listType)
   }
}