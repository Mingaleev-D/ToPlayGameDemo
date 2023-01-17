package com.example.toplaygamedemo.ui.fragments.game.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.toplaygamedemo.R
import com.example.toplaygamedemo.common.Constants.DEFAULT_CATEGORY
import com.example.toplaygamedemo.common.Constants.DEFAULT_PLATFORM
import com.example.toplaygamedemo.ui.fragments.game.GameQueriesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class GameBottomSheetFragment : BottomSheetDialogFragment() {

   private val viewModel by viewModels<GameQueriesViewModel>()

   private var platformChip = DEFAULT_PLATFORM
   private var platformTypeChipId = 0
   private var categoryTypeChip = DEFAULT_CATEGORY
   private var categoryTypeChipId = 0

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      val mView = inflater.inflate(R.layout.fragment_game_bottom_sheet, container, false)

      viewModel.readGameType.asLiveData().observe(viewLifecycleOwner) {
         platformChip = it.selectedAllPlatformT
         categoryTypeChip = it.selectedCategoryT
         updateChip(it.selectedPlatformTId, mView.findViewById<ChipGroup>(R.id.platformChipGroup))
         updateChip(it.selectedCategoryTId, mView.findViewById<ChipGroup>(R.id.categoryChipGroup))
      }

      mView.findViewById<ChipGroup>(R.id.platformChipGroup)
         .setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            val selectedGamePlatformType = chip.text.toString().toLowerCase(Locale.ROOT)

            platformChip = selectedGamePlatformType
            platformTypeChipId = checkedId
         }

      mView.findViewById<ChipGroup>(R.id.categoryChipGroup)
         .setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            val selectedGameCategoryType = chip.text.toString().toLowerCase(Locale.ROOT)

            categoryTypeChip = selectedGameCategoryType
            categoryTypeChipId = checkedId
         }

      mView.findViewById<Button>(R.id.buttonApply).setOnClickListener {
         viewModel.saveGameType(
            platformChip,
            platformTypeChipId,
            categoryTypeChip,
            categoryTypeChipId
         )

         val action =
            GameBottomSheetFragmentDirections.actionGameBottomSheetFragmentToGameFragment(true)
         findNavController().navigate(action)
      }



      return mView
   }

   private fun updateChip(chipId: Int, findViewByIdChipGroup: ChipGroup?) {
      if (chipId != 0) {
         try {
            findViewByIdChipGroup?.findViewById<Chip>(chipId)?.isChecked = true
         } catch (e: Exception) {
            Log.d("TAG", "updateChip: ${e.message}")
         }
      }
   }


}