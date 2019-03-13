package functional.abstractions.monoid

import functional.abstractions.functor.Failure
import functional.abstractions.functor.Success
import functional.abstractions.functor.Try
import functional.abstractions.functor.fold

fun <A> Try<A>.plus(aTry: Try<A>, fn: (A, A) -> A): Try<A> =
        fold(transformFailure = { Failure(it) },
                transformSuccess = { value ->
                    aTry.fold(
                            transformFailure = { Failure(it) },
                            transformSuccess = { Success(fn(value, it)) })
                })
