package com.zachriek.zquran.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.zachriek.domain.Ayat
import com.zachriek.zquran.R
import com.zachriek.zquran.presentation.diffutil.AyatDiffUtil
import com.zachriek.zquran.presentation.viewholder.AyatViewHolder

class AyatAdapter : ListAdapter<Ayat, AyatViewHolder>(
    AyatDiffUtil()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyatViewHolder {
        return AyatViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_ayat,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AyatViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}