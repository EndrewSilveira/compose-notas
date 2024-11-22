package com.e.rezende.notas.base

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Parent class for all ViewModels in the app
 *
 * @param dispatcher - The CoroutineDispatcher is injected in the ViewModel for unit testing purposes
 */
abstract class BaseViewModel(
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    /**
     * Function to substitute viewModelScope.launch, this should be used in all suspend actions
     * inside a ViewModel
     *
     * @param exceptionHandler optional runnable to handle a thrown exception
     * @param loaderLiveData optional LiveData that indicates the loading state of this suspend function
     * @param function suspend runnable to perform asynchronous tasks
     *
     * @return Returns the [Job] created by this coroutine
     */
    protected fun defaultLaunch(
        exceptionHandler: ((Throwable) -> Unit)? = null,
        function: suspend () -> Unit
    ): Job {
        return viewModelScope.launch(dispatcher) {
            try {
                function.invoke()
            } catch (e: Throwable) {
                e.printStackTrace()
                exceptionHandler?.invoke(e)
            } finally {
            }
        }
    }
}