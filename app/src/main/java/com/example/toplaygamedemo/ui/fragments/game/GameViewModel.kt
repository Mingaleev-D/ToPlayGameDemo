package com.example.toplaygamedemo.ui.fragments.game

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toplaygamedemo.R
import com.example.toplaygamedemo.common.NetworkResource
import com.example.toplaygamedemo.common.isOnline
import com.example.toplaygamedemo.data.dto.GameListResponseDto
import com.example.toplaygamedemo.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
   private val app: Application,
   private val repository: GameRepository
) : ViewModel() {

   private val _gameResponse: MutableLiveData<NetworkResource<GameListResponseDto>> =
      MutableLiveData()
   val gameResponse: LiveData<NetworkResource<GameListResponseDto>> get() = _gameResponse

   fun getGame(queries: Map<String, String>) = viewModelScope.launch {
      _gameResponse.value = NetworkResource.Loading()

      if (isOnline(app)) {
         try {
            val reposne = repository.remote.fetchGame(queries)
            if (reposne.isSuccessful) {
               reposne.body()?.let { res ->
                  _gameResponse.value = NetworkResource.Success(res)
               }
            } else {
               _gameResponse.value = NetworkResource.Error(message = reposne.message())
            }
         } catch (e: Exception) {
            _gameResponse.value = NetworkResource.Error(R.string.data_not_found.toString())
         }
      } else {
         _gameResponse.value = NetworkResource.Error(R.string.no_internet_connected.toString())
      }

   }

}