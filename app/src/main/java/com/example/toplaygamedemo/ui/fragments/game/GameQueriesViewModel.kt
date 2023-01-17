package com.example.toplaygamedemo.ui.fragments.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toplaygamedemo.common.Constants.DEFAULT_CATEGORY
import com.example.toplaygamedemo.common.Constants.DEFAULT_PLATFORM
import com.example.toplaygamedemo.common.Constants.QUERY_CATEGORY
import com.example.toplaygamedemo.common.Constants.QUERY_PLATFORM
import com.example.toplaygamedemo.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 10/01/2023
 */

@HiltViewModel
class GameQueriesViewModel @Inject constructor(
   private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

   private var gamePlatformType = DEFAULT_PLATFORM
   private var categoryType = DEFAULT_CATEGORY

   val readGameType = dataStoreRepository.readAllPlatformAndCategoryType

   fun saveGameType(
      allPlatformType: String,
      allPlatformId: Int,
      categoryType: String,
      categoryId: Int
   ) = viewModelScope.launch {
      dataStoreRepository.saveAllPlatformAndCategoryType(
         allPlatformType,
         allPlatformId,
         categoryType,
         categoryId
      )
   }

   fun gameQueries(): HashMap<String, String> {
      val queries: HashMap<String, String> = HashMap()

      viewModelScope.launch {
         readGameType.collect{ value ->
            gamePlatformType = value.selectedAllPlatformT
            categoryType = value.selectedCategoryT
         }
      }

      queries[QUERY_PLATFORM] = gamePlatformType
      queries[QUERY_CATEGORY] = categoryType

      return queries
   }
}