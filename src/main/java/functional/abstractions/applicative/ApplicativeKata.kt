package functional.abstractions.applicative

import functional.abstractions.functor.Try
import functional.abstractions.functor.map
import functional.abstractions.monad.flatMap

data class Foo(val a: String, val b: Int)

fun <A, B> Try<A>.apply(tryAB: Try<(A) -> B>): Try<B> =
        flatMap { a -> tryAB.map { it(a) } }

infix fun <A, B> ((A) -> B).map(aTry: Try<A>): Try<B> = aTry.map(this)

infix fun <A, B> Try<(A) -> B>.ap(aTry: Try<A>): Try<B> = aTry.apply(this)

fun <A, B, C> ((A, B) -> C).curried(): (A) -> (B) -> C =
        { a -> { b -> this(a, b) } }

fun main(args: Array<String>) {
    ::Foo.curried() map
            Try { "hello" } ap
            Try { 3 }
}