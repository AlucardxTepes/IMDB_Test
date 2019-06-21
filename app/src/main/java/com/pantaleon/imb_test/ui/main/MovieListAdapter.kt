package com.pantaleon.imb_test.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pantaleon.imb_test.R
import com.pantaleon.imb_test.data.model.Movie
import com.pantaleon.imb_test.di.IMDB_BASE_IMAGE_URL
import com.pantaleon.imb_test.util.BindableAdapter
import kotlinx.android.synthetic.main.movie_item.view.*


class MovieListAdapter(private val actionDelegate: MovieItemActionDelegate) : RecyclerView.Adapter<MovieViewHolder>(),
    BindableAdapter<Movie> {

    private var data = emptyList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater.inflate(R.layout.movie_item, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener {
            actionDelegate.onMovieClicked(data[position])
        }
    }

    /**
     * Function used by databinding to set data from the view
     */
    override fun setData(data: List<Movie>) {
        this.data = data
        notifyDataSetChanged()
    }
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(movie: Movie) {
        with(itemView) {
            title.text = movie.title
            rating.text = movie.voteAverage.toString()
            ratingBar.rating = movie.voteAverage / 2
            if (movie.posterPath != null) {
                Glide.with(context)
                    .load(IMDB_BASE_IMAGE_URL + "/w200" + movie.posterPath)
                    .into(movieImageView)
            } else {
                Glide.with(context)
                    .load(context.getDrawable(R.drawable.movie_placeholder))
                    .into(movieImageView)
            }
        }
    }
}