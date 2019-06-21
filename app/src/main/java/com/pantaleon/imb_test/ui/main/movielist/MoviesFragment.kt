package com.pantaleon.imb_test.ui.main.movielist

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.pantaleon.imb_test.MoviesApp
import com.pantaleon.imb_test.R
import com.pantaleon.imb_test.data.model.Movie
import com.pantaleon.imb_test.databinding.FragmentMoviesBinding
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_movies.view.*
import javax.inject.Inject

/**
 * Fragment containing Movies List for current year
 */
class MoviesFragment : Fragment(), MovieItemActionDelegate {

    private lateinit var viewModel: MoviesViewModel
    private var sortDialog: AlertDialog? = null

    @Inject
    lateinit var viewModelFactory: MoviesViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        // Inject Dagger Dependencies
        MoviesApp.getAppComponent(context!!).inject(this)
        // Init viewModel
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMoviesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Init RecyclerView
        with(view.recycler_view) {
            adapter = MovieListAdapter(this@MoviesFragment)
            layoutManager = GridLayoutManager(context, resources.getInteger(R.integer.movie_grid_columns))
        }
        // Init refreshView
        refreshLayout.setOnRefreshListener {
            viewModel.fetchData(true)
        }
        viewModel.reloading.observe(this, Observer {
            refreshLayout.isRefreshing = it
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchData()
    }

    override fun onMovieClicked(movie: Movie) {
        val action = MoviesFragmentDirections.toMovieDetail(
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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_movies_fragment, menu)
        initSearchView(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_item_sort -> {
                showSortDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)
        // Tint menu items white
        menu?.findItem(R.id.menu_item_sort)?.icon?.mutate()?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
        menu?.findItem(R.id.menu_item_search)?.icon?.mutate()?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
    }

    private fun showSortDialog() {
        if (sortDialog == null) {
            val builder = AlertDialog.Builder(context!!)

            // Populate search options
            val arrayAdapter = ArrayAdapter<String>(context!!, android.R.layout.simple_list_item_1)
            arrayAdapter.addAll("Popularity", "Title", "Rating", "Release Date")

            builder.setTitle(getString(R.string.action_sort))
            builder.setAdapter(arrayAdapter) { _, which ->
                viewModel.sortBy(arrayAdapter.getItem(which)!!)
            }
            sortDialog = builder.show()
        } else {
            sortDialog?.show()
        }
    }

    /**
     * Initializes searchView to forward text events to ViewModel
     */
    private fun initSearchView(menu: Menu?) {
        val searchView = menu?.findItem(R.id.menu_item_search)?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.search(it)
                }
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    searchView.clearFocus()
                }
                return false
            }
        })
    }
}

interface MovieItemActionDelegate {
    fun onMovieClicked(movie: Movie)
    fun onFavoriteButtonClicked(movie: Movie)
}