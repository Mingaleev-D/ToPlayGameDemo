package com.example.toplaygamedemo.ui.fragments.game.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.toplaygamedemo.common.load
import com.example.toplaygamedemo.data.dto.GameListResponseDtoItem
import com.example.toplaygamedemo.databinding.ItemGameBinding

/**
 * @author : Mingaleev D
 * @data : 10/01/2023
 */

class GameAdapter : RecyclerView.Adapter<GameAdapter.MyViewHolder>() {

   inner class MyViewHolder(val binding: ItemGameBinding) :
      RecyclerView.ViewHolder(binding.root) {
      @SuppressLint("SetTextI18n")
      fun bind(game: GameListResponseDtoItem) {
         binding.apply {
            txtTitle.text = "title: " + game.title
            txtGenre.text = "genre: " + game.genre

            posterView.load(game.thumbnail)
         }
      }

   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return MyViewHolder(binding)
   }

   override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      val gameResult = differ.currentList[position]
      holder.bind(gameResult)
   }

   override fun getItemCount(): Int {
      return differ.currentList.size
   }

   private val callback = object : DiffUtil.ItemCallback<GameListResponseDtoItem>() {
      override fun areItemsTheSame(
         oldItem: GameListResponseDtoItem,
         newItem: GameListResponseDtoItem
      ): Boolean {
         return oldItem.id == newItem.id
      }

      override fun areContentsTheSame(
         oldItem: GameListResponseDtoItem,
         newItem: GameListResponseDtoItem
      ): Boolean {
         return oldItem == newItem
      }
   }
   val differ = AsyncListDiffer(this, callback)
}