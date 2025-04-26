package com.example.composesamples.lists

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ListViewModel: ViewModel() {

    var scrollState = mutableStateOf(LazyListState())

    fun generateStringList(): List<String> {
        val list = mutableListOf<String>()
        for (i in 1..100) {
            list.add("String $i")
        }
        return list
    }

}