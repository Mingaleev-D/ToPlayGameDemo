package com.example.toplaygamedemo.ui.fragments.game.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.toplaygamedemo.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class GameBottomSheetFragment : BottomSheetDialogFragment() {


   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      // Inflate the layout for this fragment
      return inflater.inflate(R.layout.fragment_game_bottom_sheet, container, false)
   }


}