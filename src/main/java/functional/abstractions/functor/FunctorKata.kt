// Copyright © FunctionalHub.com 2018. All rights reserved.

package functional.abstractions.functor

data class PredicateException(override val message: String) : Exception(message)

typealias Failure<A> = Try.Failure<A>
typealias Success<A> = Try.Success<A>

//Functor
//Container with map operation:
//Try<A>.map(A ->B) -> Try<B>

sealed class Try<out A> {

    companion object {
        operator fun <A> invoke(f: () -> A): Try<A> =
                try {
                    Success(f())
                } catch (t: Throwable) {
                    Failure(t)
                }
    }

    class Success<out A>(val value: A) : Try<A>()
    class Failure<out A>(val exception: Throwable) : Try<A>()

}

fun <A> Try<A>.filter(predicate: (A) -> Boolean): Try<A> = fold(
        transformFailure = { Failure(it) },
        transformSuccess = {
            if (predicate(it)) {
                Success(it)
            } else {
                Failure(PredicateException("Predicate does not hold for $it"))
            }
        }
)

fun <A, B> Try<A>.fold(transformFailure: (Throwable) -> B, transformSuccess: (A) -> B): B = when (this) {
    is Try.Success -> try {
        transformSuccess(value)
    } catch (t: Throwable) {
        transformFailure(t)
    }
    is Try.Failure -> transformFailure(exception)
}

fun <A> Try<A>.get(): A = when (this) {
    is Try.Success -> value
    is Try.Failure -> throw exception
}

fun <A> Try<A>.isFailure(): Boolean = this is Failure

fun <A> Try<A>.isSuccess(): Boolean = this is Success

fun <A, B> Try<A>.map(transform: (A) -> B): Try<B> = fold(
        transformFailure = { Failure(it) },
        transformSuccess = { Success(transform(it)) })

