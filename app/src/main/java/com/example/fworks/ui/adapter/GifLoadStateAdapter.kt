package com.example.fworks.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.fworks.databinding.LayoutProgressbarBinding


class GifLoadStateAdapter( val retry :()->Unit): LoadStateAdapter<GifLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = LayoutProgressbarBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LoadStateViewHolder(binding)
    }

    inner class LoadStateViewHolder(private val binding: LayoutProgressbarBinding):
        RecyclerView.ViewHolder(binding.root)
    {
        init{
            binding.btnRetry.setOnClickListener{retry.invoke()}
        }

        fun bind(loadState: LoadState)
        {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
                lblLoadingError.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}