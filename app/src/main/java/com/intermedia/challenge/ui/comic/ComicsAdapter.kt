package com.intermedia.challenge.ui.comic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intermedia.challenge.R
import com.intermedia.challenge.data.models.Appearance
import com.intermedia.challenge.databinding.ViewCharacterItemBinding
import com.intermedia.challenge.ui.base.BaseAdapter


class ComicsAdapter: BaseAdapter<Appearance, ComicsAdapter.ComicsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder =
        ComicsViewHolder(
            ViewCharacterItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_character_item,
                    parent,
                    false
                )
            )
        )

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ComicsViewHolder(
        private val binding: ViewCharacterItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Appearance)  {
            binding.character = item
        }
    }
}