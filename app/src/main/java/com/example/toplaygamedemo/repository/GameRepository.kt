package com.example.toplaygamedemo.repository

import com.example.toplaygamedemo.data.datasource.LocalDataSource
import com.example.toplaygamedemo.data.datasource.RemoteDataSource
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 10/01/2023
 */

class GameRepository @Inject constructor(
   remoteDataSource: RemoteDataSource,
   localDataSource: LocalDataSource
) {

   val remote = remoteDataSource
   val local = localDataSource
}