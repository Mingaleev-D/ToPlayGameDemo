package com.example.toplaygamedemo.di

import android.content.Context
import androidx.room.Room
import com.example.toplaygamedemo.common.Constants.DATABASE_NAME
import com.example.toplaygamedemo.data.local.db.GameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

   @Provides
   @Singleton
   fun provideDatabase(@ApplicationContext context:Context) =
      Room.databaseBuilder(context,GameDatabase::class.java,DATABASE_NAME).build()

   @Provides
   @Singleton
   fun provideDao(database:GameDatabase) = database.gameDao()
}