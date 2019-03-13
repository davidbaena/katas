package functional.abstractions.monad

import functional.abstractions.functor.*


//Monad is a Monoid with Flatmap
//Try<A>.flatMap((A) -> Try<B>) ->Try<B>

fun <A, B> Try<A>.flatMap(transform: (A) -> Try<B>): Try<B> =
        fold(transformFailure = {
            Failure(it)
        }, transformSuccess = { a ->
            transform(a).fold(
                    transformFailure = { Failure(it) },
                    transformSuccess = { value -> Success(value) }
            )
        })

fun main(args: Array<String>) {
    println(Try { "a" }.flatMap { Try { "b $it" } }.get())
}
