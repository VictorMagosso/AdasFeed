package com.victor.adasfeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {

    init {
        viewModelScope.launch {
            doSomethingAsyncInViewModel()
        }

        viewModelScope.launch {
            doSomethingElseAsyncInViewModel()
        }
    }

    private suspend fun doSomethingAsyncInViewModel() {
        delay(4000L)
        println("mensagem")
    }

    private suspend fun doSomethingElseAsyncInViewModel() {
        delay(900000000L)
        println("mensagem")
    }
}