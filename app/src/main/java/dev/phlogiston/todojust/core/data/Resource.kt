package dev.phlogiston.todojust.core.data

// A generic class that contains data and status about loading this data.
sealed class Resource<out T>(
   val data: T? = null,
   val message: String? = null
) {
   class Success<T>(data: T) : Resource<T>(data)
   class Loading<T>(data: T? = null) : Resource<T>(data)
   class Error<T>(val failure: Failure, data: T? = null) : Resource<T>(data, failure.message())

    fun <A, B> mergeWith(another: Resource<A>, transform: (T?, A?) -> B): Resource<B> {
        if (this is Error) {
            return Error(this.failure)
        }
        if (another is Error) {
            return Error(another.failure)
        }
        return Success(transform(this.data, another.data))
    }

    fun <B> map(transform: (T?) -> B): Resource<B> =
        when (this) {
            is Success -> Success(transform(this.data))
            is Error -> Error(this.failure, transform(this.data))
            is Loading -> Loading(transform(this.data))
        }
}