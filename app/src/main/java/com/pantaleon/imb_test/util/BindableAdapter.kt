package com.pantaleon.imb_test.util

/**
 * Adapters implementing this interface will expose a method to set their data
 * This way it can be set directly through the view using data binding
 */
interface BindableAdapter<T> {
    fun setData(data: List<T>)
}