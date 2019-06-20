package com.pantaleon.imb_test.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pantaleon.imb_test.R
import com.pantaleon.imb_test.data.model.Movie
import com.pantaleon.imb_test.util.BindableAdapter
import kotlinx.android.synthetic.main.movie_item.view.*


class MovieListAdapter : RecyclerView.Adapter<MovieViewHolder>(), BindableAdapter<Movie> {

    private var data = emptyList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater.inflate(R.layout.movie_item, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(data[position])
    }

    /**
     * Function used by databinding to set data from the view
     */
    override fun setData(movies: List<Movie>) {
        data = movies
        notifyDataSetChanged()
    }
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(movie: Movie) {
        itemView.title.text = movie.title
        itemView.rating.text = movie.voteAverage.toString()
    }
}