package com.example.toplaygamedemo.data.datasource

import com.example.toplaygamedemo.data.dto.GameListResponseDto
import com.example.toplaygamedemo.data.remote.service.ApiService
import retrofit2.Response
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 10/01/2023
 */

class RemoteDataSource @Inject constructor(
   private val api: ApiService
) {

   suspend fun fetchGame(queries: Map<String, String>): Response<GameListResponseDto> {
      return api.fetchGames(queries)
   }
}