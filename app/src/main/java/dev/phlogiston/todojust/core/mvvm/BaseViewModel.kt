package dev.phlogiston.todojust.core.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.phlogiston.todojust.core.data.Failure
import dev.phlogiston.todojust.core.data.Repository
import dev.phlogiston.todojust.core.data.Resource
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger

open class BaseViewModel : ViewModel() {

    private val loadingSemaphore = AtomicInteger(0)

    private val mLoading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean>
        get() = mLoading

    protected val mFailure: MutableLiveData<Event<Failure>> = MutableLiveData()
    val failure: LiveData<Event<Failure>>
        get() = mFailure

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    protected fun decrementFailure(failure: Failure) {
        this.mFailure.value = Event(failure)
        decrementLoading()
    }

    protected fun incrementLoading() {
        val current = loadingSemaphore.getAndIncrement()
        if (current == 0) {
            this.mLoading.value = true
        }
    }

    protected fun decrementLoading() {
        val current = loadingSemaphore.get()
        if (current > 0) {
            val result = loadingSemaphore.decrementAndGet()
            if (result == 0) {
                mLoading.value = false
            }
        }
    }

    protected fun <T> launch(
        repo: Repository<T?, Any>,
        doOnFailure: (Failure) -> Unit = {},
        doOnSuccess: (T?) -> Unit = {}
    ) {
        launch(repo, Unit, doOnFailure = doOnFailure, doOnSuccess = doOnSuccess)
    }

    protected fun <T, Params> launch(
        repo: Repository<T?, Params>,
        params: Params,
        doOnFailure: (Failure) -> Unit = {},
        doOnSuccess: (T?) -> Unit
    ) {
        viewModelScope.launch {
            repo(params).collect {
                handle(it, doOnFailure = {
                    doOnFailure(it)
                }) { result ->
                    doOnSuccess(it.data)
                }
            }
        }
    }

    protected fun <T> handle(
        resource: Resource<T>,
        doOnFailure: (Failure) -> Unit = {},
        doOnSuccess: (Resource.Success<T>) -> Unit
    ) {
        when (resource) {
            is Resource.Loading -> incrementLoading()
            is Resource.Error -> {
                decrementFailure(resource.failure)
                doOnFailure(resource.failure)
            }
            is Resource.Success -> {
                decrementLoading()
                doOnSuccess(resource)
            }
        }
    }

    protected fun launch(func: suspend () -> Unit) {
        viewModelScope.launch {
            func.invoke()
        }
    }

}