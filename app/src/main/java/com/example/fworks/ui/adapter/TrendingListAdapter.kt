package com.example.fworks.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fworks.data.model.TredingGifImage

import com.example.fworks.databinding.LayoutGifListItemBinding
import com.example.fworks.viewmodel.ViewModelMain

class TrendingListAdapter(private val viewModel: ViewModelMain): PagingDataAdapter<TredingGifImage,MainViewHolder>(GIF_COMPARATOR) {

    companion object{
        private val GIF_COMPARATOR = object : DiffUtil.ItemCallback<TredingGifImage> (){
            override fun areItemsTheSame(
                oldItem: TredingGifImage,
                newItem: TredingGifImage
            ) = oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: TredingGifImage,
                newItem: TredingGifImage
            ) = oldItem == newItem
        }
    }
  /*  fun setDataList(movies: List<TredingGifImage>) {
        this.gifList = movies.toMutableList()
        notifyDataSetChanged()
    }*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutGifListItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val gif = getItem(position)
        holder.binding.tvGifTitle.text = gif!!.title
       // holder.binding.tvRating.text = gif.rating

       /* Glide.with(holder.itemView.context)
            .load(gif.images!!.orignal!!.url)
            .apply(RequestOptions.overrideOf(400,200))
            .into(holder.binding.ivTrendingGif);*/
        Glide.with(holder.itemView.context).load(gif.images!!.orignal!!.url).into(holder.binding.ivTrendingGif)
        holder.binding.btnFavourite.setOnClickListener{
           // viewModel.insert(FavouriteGif(gif.id!!,gif.title,gif.images!!.orignal!!.url!!))
            viewModel.setSelectedITem(gif)
            //FavouriteGif(gif.id!!,gif.title,gif.images!!.orignal!!.url!!)
        }
    }
}

class MainViewHolder(val binding: LayoutGifListItemBinding) : RecyclerView.ViewHolder(binding.root) {

}


