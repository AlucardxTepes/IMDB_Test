package com.pantaleon.imb_test.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.pantaleon.imb_test.MoviesApp
import com.pantaleon.imb_test.data.model.Movie
import com.pantaleon.imb_test.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.view.*
import javax.inject.Inject

/**
 * Fragment containing Movies List for current year
 */
class MoviesFragment : Fragment(), MovieItemActionDelegate {

    private lateinit var viewModel: MoviesViewModel

    @Inject
    lateinit var viewModelFactory: MoviesViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inject Dagger Dependencies
        MoviesApp.getAppComponent(context!!).inject(this)
        // Init viewModel
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Init RecyclerView
        with(view.recycler_view) {
            adapter = MovieListAdapter(this@MoviesFragment)
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun onMovieClicked(movie: Movie) {
        // Use Navigation action and include movie ID as argument
        findNavController().navigate(MoviesFragmentDirections.toMovieDetail(movie.id.toInt()))
    }
}

interface MovieItemActionDelegate {
    fun onMovieClicked(movie: Movie)
}