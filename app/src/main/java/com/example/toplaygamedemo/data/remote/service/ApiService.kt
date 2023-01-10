package com.example.toplaygamedemo.data.remote.service

import com.example.toplaygamedemo.data.dto.GameListResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @author : Mingaleev D
 * @data : 10/01/2023
 */

interface ApiService {

   @GET("games")
   suspend fun fetchGames(
      @QueryMap queries:Map<String,String>
   ):Response<GameListResponseDto>
}