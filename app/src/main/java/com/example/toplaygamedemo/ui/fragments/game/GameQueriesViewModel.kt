package com.example.toplaygamedemo.ui.fragments.game

import androidx.lifecycle.ViewModel
import com.example.toplaygamedemo.common.Constants.QUERY_CATEGORY
import com.example.toplaygamedemo.common.Constants.QUERY_PLATFORM

/**
 * @author : Mingaleev D
 * @data : 10/01/2023
 */

class GameQueriesViewModel :ViewModel() {

   fun gameQueries(): HashMap<String, String> {
      val queries: HashMap<String, String> = HashMap()

      queries[QUERY_PLATFORM] = "pc"
      queries[QUERY_CATEGORY] = "mmorpg"

      return queries
   }
}