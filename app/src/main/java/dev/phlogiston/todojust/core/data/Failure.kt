package dev.phlogiston.todojust.core.data

import android.util.Log
import dev.phlogiston.todojust.core.data.Failure.FeatureFailure
import java.io.IOException

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {

    object Io : Failure() {
        override fun message(): String = "Ошибка данных"
    }

    abstract fun message(): String

    /** * Extend this class for feature specific failures.*/
    open class FeatureFailure(private val error: String) : Failure() {
        override fun message(): String = error
    }

    companion object {
        fun default() = FeatureFailure("Неизвестная ошибка")
        fun handle(exception: Exception): Failure {
            Log.e("Failure", "in handle", exception)
            return when {
                exception is IOException -> Io
                else -> {
                    exception.localizedMessage?.let { FeatureFailure(it) } ?: default()
                }
            }
        }

        fun handle(throwable: Throwable): Failure {
            Log.e("Failure", "in handle", throwable)
            return when {
                throwable is IOException -> Io
                else -> {
                    throwable.localizedMessage?.let { FeatureFailure(it) } ?: default()
                }
            }
        }

        fun handle(message: String?): Failure =
            when {
//                message?.contains("Пользователь с данным номером не найден") == true ->
//                    Io
                else -> default()
            }
    }
}
