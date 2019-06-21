package com.pantaleon.imb_test.ui.main.favorites


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.pantaleon.imb_test.MoviesApp
import com.pantaleon.imb_test.R
import com.pantaleon.imb_test.data.model.Movie
import com.pantaleon.imb_test.databinding.FragmentFavoritesBinding
import com.pantaleon.imb_test.ui.main.movielist.MovieItemActionDelegate
import com.pantaleon.imb_test.ui.main.movielist.MovieListAdapter
import kotlinx.android.synthetic.main.fragment_movies.view.*
import javax.inject.Inject


/**
 * Fragment containing Movies markeed as favorite
 */
class FavoritesFragment : Fragment(), MovieItemActionDelegate {

    private lateinit var viewModel: FavoritesViewModel

    @Inject
    lateinit var viewModelFactory: FavoritesViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        // Inject Dagger Dependencies
        MoviesApp.getAppComponent(context!!).inject(this)
        // Init viewModel
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FavoritesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Init RecyclerView
        with(view.recycler_view) {
            adapter = MovieListAdapter(this@FavoritesFragment)
            layoutManager = GridLayoutManager(context, resources.getInteger(R.integer.movie_grid_columns))
        }
    }

    override fun onMovieClicked(movie: Movie) {
        val action = FavoritesFragmentDirections.toMovieDetailFromFavorites(
            movie.id.toInt(),
            movie.title,
            movie.backdropPath ?: movie.posterPath ?: ""
        )
        findNavController().navigate(action)
    }

    /**
     * Toggle favorite status for selected movie
     */
    override fun onFavoriteButtonClicked(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        viewModel.addFavoriteMovie(movie)
    }

}
