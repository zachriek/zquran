package com.zachriek.zquran.presentation.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.zachriek.domain.Ayat

class AyatDiffUtil: DiffUtil.ItemCallback<Ayat>() {
    override fun areItemsTheSame(oldItem: Ayat, newItem: Ayat): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Ayat, newItem: Ayat): Boolean {
        return oldItem.number == newItem.number
    }
}