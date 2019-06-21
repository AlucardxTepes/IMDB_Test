package com.pantaleon.imb_test.util

import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Sets the recyclerview's adapter data
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter("data")
fun <T> setRecyclerViewData(recyclerView: RecyclerView, data: List<T>) {
    if (recyclerView.adapter is BindableAdapter<*>) {
        (recyclerView.adapter as BindableAdapter<T>).setData(data)
    }
}

@BindingAdapter("text")
fun setToolbarText(toolbar: Toolbar, text: String) {
    toolbar.title = text
}

@BindingAdapter("vote_count")
fun showVoteCount(textView: TextView, votes: String) {
    textView.text = "Total Votes: $votes"
}