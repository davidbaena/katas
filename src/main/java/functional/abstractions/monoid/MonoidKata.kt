package functional.abstractions.monoid

import functional.abstractions.functor.*


//Semigroup: A (op) A -> A
// EJ: 1+1=2
//Identity: e (op) b -> b
// EJ: 0+1=1
//Associativity
// (a + b) + c = a + (b +c)

fun <A> Try<A>.plus(aTry: Try<A>, fn: (A, A) -> A): Try<A> =
        fold(transformFailure = { Failure(it) },
                transformSuccess = { value ->
                    aTry.fold(
                            transformFailure = { Failure(it) },
                            transformSuccess = { Success(fn(value, it)) })
                })

fun main(args: Array<String>) {
    val isAsemigroup = Try { "a" }.plus(Try { "b" }, concat()).get() == "ab"

    val hasIdentity = Try { "a" }.plus(Try { "" }, concat()).get() == "a"

    val left = (Try { "a" }.plus(Try { "b" }, concat())).plus(Try { "c" }, concat()).get()
    val right = Try { "a" }.plus((Try { "b" }.plus(Try { "c" }, concat())), concat()).get()
    val hasAssociativity = left == right


    println("First Law: $isAsemigroup: a ++ b = ab")
    println("Second Law: $hasIdentity: a ++ '' = a")
    println("Third Law: $hasAssociativity: ab++c = a++bc = abc")

}

private fun concat(): (String, String) -> String = { a1, a2 ->
    a1 + a2
}
