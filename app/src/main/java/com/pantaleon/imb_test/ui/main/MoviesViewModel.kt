package com.pantaleon.imb_test.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MoviesViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Viendo tab numero: $it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}