package dev.phlogiston.todojust.core.data

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

abstract class Repository<Type, in Params> {

    abstract suspend fun run(params: Params): Flow<Resource<Type>>

    open val doOnSuccess: (Type?, Params) -> Unit = { _, _ -> }

    protected var scope: CoroutineScope? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("Repository", "exceptionHandler", throwable)
    }

    open suspend operator fun invoke(params: Params): Flow<Resource<Type>> {
        scope?.cancel()
        val newScope = CoroutineScope(Dispatchers.Main + SupervisorJob() + exceptionHandler)
        scope = newScope
        return run(params).onEach {
            if (it is Resource.Success) {
                doOnSuccess(it.data, params)
            }
        }
            .flowOn(Dispatchers.IO)
            .shareIn(newScope, SharingStarted.WhileSubscribed(), 1)
    }

    protected suspend fun <T> execute(
        showLoading: Boolean = true,
        invoke: suspend () -> T
    ): Flow<Resource<T>> =
        flow<Resource<T>> {
            try {
                val data = invoke()
                emit(Resource.Success(data))
            } catch (ex: Exception) {
                emit(Failure.handle(ex).let {
                    Resource.Error(it)
                })
            }
        }.flowOn(Dispatchers.IO).onStart { if (showLoading) emit(Resource.Loading()) }

    protected suspend fun <T> executeDb(
        showLoading: Boolean = true,
        invoke: suspend () -> Flow<T>
    ): Flow<Resource<T>> = invoke()
        .flowOn(Dispatchers.IO)
        .filterNotNull()
        .map<T, Resource<T>> { Resource.Success(it) }
        .onStart { if (showLoading) emit(Resource.Loading()) }
        .catch { e ->
            emit(Failure.handle(e).let {
                Resource.Error(it)
            })
        }

}