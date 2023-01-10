package com.example.toplaygamedemo.ui.fragments.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toplaygamedemo.R
import com.example.toplaygamedemo.common.NetworkResource
import com.example.toplaygamedemo.databinding.FragmentGameBinding
import com.example.toplaygamedemo.ui.fragments.game.adapter.GameAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GameFragment : Fragment() {

   private var _binding: FragmentGameBinding? = null
   private val binding by lazy { _binding!! }
   private val viewModel by viewModels<GameViewModel>()
   private val gameAdapter = GameAdapter()

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentGameBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      initRecyclerView()
      viewModelInit()
   }

   private fun viewModelInit() {
      lifecycleScope.launch {
         viewModel.getGame(gameQueries())
         viewModel.gameResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
               is NetworkResource.Loading -> {
                  binding.rvAllGame.showShimmer()
               }
               is NetworkResource.Success -> {
                  binding.rvAllGame.hideShimmer()
                  response.data?.let {
                     gameAdapter.differ.submitList(it)
                  }
               }
               is NetworkResource.Error   -> {
                  binding.imageViewError.visibility = View.VISIBLE
                  binding.txtError.visibility = View.VISIBLE
                  Toast.makeText(
                     requireContext(),
                     getString(R.string.no_internet_connected),
                     Toast.LENGTH_SHORT
                  ).show()
               }
            }
         }
      }
   }

   private fun initRecyclerView() {
      with(binding.rvAllGame) {
         adapter = gameAdapter
         overScrollMode = View.OVER_SCROLL_NEVER
         setHasFixedSize(true)
      }
   }

   private fun gameQueries(): HashMap<String, String> {
      val queries: HashMap<String, String> = HashMap()

      queries["platform"] = "pc"
      queries["category"] = "mmorpg"

      return queries
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}