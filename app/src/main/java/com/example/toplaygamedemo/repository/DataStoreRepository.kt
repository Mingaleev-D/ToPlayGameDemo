package com.example.toplaygamedemo.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import com.example.toplaygamedemo.common.Constants.ALL_PLATFORM_ID_PREFERENCES
import com.example.toplaygamedemo.common.Constants.ALL_PLATFORM_PREFERENCES
import com.example.toplaygamedemo.common.Constants.CATEGORY_ID_PREFERENCES
import com.example.toplaygamedemo.common.Constants.CATEGORY_PREFERENCES
import com.example.toplaygamedemo.common.Constants.DEFAULT_CATEGORY
import com.example.toplaygamedemo.common.Constants.DEFAULT_PLATFORM
import com.example.toplaygamedemo.common.Constants.PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 14/01/2023
 */

@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(
   @ApplicationContext private val context: Context
) {

   private object PreferenceKeys {
      val selectedAllPlatformType = preferencesKey<String>(ALL_PLATFORM_PREFERENCES)
      val selectedPlatformTypeId = preferencesKey<Int>(ALL_PLATFORM_ID_PREFERENCES)
      val selectedCategoryType = preferencesKey<String>(CATEGORY_PREFERENCES)
      val selectedCategoryTypeId = preferencesKey<Int>(CATEGORY_ID_PREFERENCES)

   }

   private val dataStore: DataStore<Preferences> = context.createDataStore(
      name = PREFERENCES_NAME
   )

   suspend fun saveAllPlatformAndCategoryType(
      allPlatformType: String,
      allPlatformId: Int,
      categoryType: String,
      categoryId: Int
   ) {
      dataStore.edit { preferences ->
         preferences[PreferenceKeys.selectedAllPlatformType] = allPlatformType
         preferences[PreferenceKeys.selectedPlatformTypeId] = allPlatformId
         preferences[PreferenceKeys.selectedCategoryType] = categoryType
         preferences[PreferenceKeys.selectedCategoryTypeId] = categoryId
      }
   }

   val readAllPlatformAndCategoryType: Flow<AllPlatformAndCategoryType> = dataStore.data
      .catch { exception ->
         if (exception is IOException) {
            emit(emptyPreferences())
         } else {
            throw exception
         }
      }
      .map { preferences ->
         val selectedAllPlatform =
            preferences[PreferenceKeys.selectedAllPlatformType] ?: DEFAULT_PLATFORM
         val selectedPlatformId = preferences[PreferenceKeys.selectedPlatformTypeId] ?: 0
         val selectedCategory = preferences[PreferenceKeys.selectedCategoryType] ?: DEFAULT_CATEGORY
         val selectedCategoryId = preferences[PreferenceKeys.selectedCategoryTypeId] ?: 0
         AllPlatformAndCategoryType(
            selectedAllPlatform,
            selectedPlatformId,
            selectedCategory,
            selectedCategoryId
         )
      }

}

data class AllPlatformAndCategoryType(
   val selectedAllPlatformT: String,
   val selectedPlatformTId: Int,
   val selectedCategoryT: String,
   val selectedCategoryTId: Int
)
