package com.app.excelli

import Search
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.excelli.databinding.ItemMoviesListBinding
import com.squareup.picasso.Picasso

class MovieListAdapter(private var moviesList: ArrayList<Search>, private var context: Context) :
    RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    var onItemClick: ((Search) -> Unit)? = null

    class MovieListViewHolder(itemBinding: ItemMoviesListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val itemBinding: ItemMoviesListBinding

        init {
            this.itemBinding = itemBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMoviesListBinding.inflate(inflater, parent, false)
        return MovieListViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val currentItem = moviesList[position]
        holder.itemBinding.mvTitle.text = currentItem.title
        holder.itemBinding.mvDesc.text = currentItem.year
        holder.itemBinding.movieItemParent.setOnClickListener({
            onItemClick?.invoke(currentItem)
        })
        Picasso.with(context).load(currentItem.poster).into(holder.itemBinding.imageView)
    }

    override fun getItemCount() = moviesList.size

    fun setNewData(topic: ArrayList<Search>?) {
        if (topic != null) {
            moviesList = topic
            notifyDataSetChanged()
        }
    }
}
