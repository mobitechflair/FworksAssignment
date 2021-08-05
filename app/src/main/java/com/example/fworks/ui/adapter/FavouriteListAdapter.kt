package com.example.fworks.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fworks.data.db.entity.FavouriteGif
import com.example.fworks.databinding.LayoutGifFavouriteItemBinding
import com.example.fworks.viewmodel.FavouriteViewModel

class FavouriteListAdapter(private var gifList: MutableList<FavouriteGif>, val viewModel: FavouriteViewModel): RecyclerView.Adapter<FavouriteViewHolder>() {

    fun setDataList(movies: List<FavouriteGif>) {
        this.gifList = movies.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        return FavouriteViewHolder(
            LayoutGifFavouriteItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val gif = gifList[position]
        holder.binding.tvGifTitle.text = gif.title
        // holder.binding.tvRating.text = gif.rating
        /* Glide.with(holder.itemView.context)
             .load(gif.images!!.orignal!!.url)
             .apply(RequestOptions.overrideOf(400,200))
             .into(holder.binding.ivTrendingGif);*/
        holder.binding.tvRemove.setOnClickListener { viewModel.delete(gif.id) }

        Glide.with(holder.itemView.context).load(gif.url).into(holder.binding.ivTrendingGif)
    }

    override fun getItemCount(): Int {
        return gifList.size
    }
}

class FavouriteViewHolder(val binding: LayoutGifFavouriteItemBinding) : RecyclerView.ViewHolder(binding.root) {

}


