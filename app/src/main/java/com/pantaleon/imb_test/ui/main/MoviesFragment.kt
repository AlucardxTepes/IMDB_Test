package com.pantaleon.imb_test.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.pantaleon.imb_test.MoviesApp
import com.pantaleon.imb_test.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.view.*
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class MoviesFragment : Fragment() {

    private lateinit var viewModel: MoviesViewModel

    @Inject
    lateinit var viewModelFactory: MoviesViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inject Dagger Dependencies
        MoviesApp.getAppComponent(context!!).inject(this)
        // Init viewModel
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
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
            adapter = MovieListAdapter()
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): MoviesFragment {
            return MoviesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}