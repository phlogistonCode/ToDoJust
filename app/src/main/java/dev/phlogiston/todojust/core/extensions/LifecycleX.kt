package dev.phlogiston.todojust.core.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import dev.phlogiston.todojust.core.mvvm.Event
import dev.phlogiston.todojust.core.mvvm.EventObserver

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) =
        liveData.observe(this, Observer(body))

fun <T : Any, L : LiveData<Event<T>>> LifecycleOwner.observeEvent(liveData: L, body: (T) -> Unit) =
        liveData.observe(this, EventObserver(body))

fun <T : Any, L : LiveData<Event<T>>> LifecycleOwner.failure(liveData: L, body: (T) -> Unit) =
        liveData.observe(this, EventObserver(body))

fun <T : Any, L : LiveData<T?>> LifecycleOwner.observeNullable(liveData: L, body: (T?) -> Unit) =
        liveData.observe(this, Observer(body))