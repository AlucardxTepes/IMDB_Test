package com.pantaleon.imb_test.util

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