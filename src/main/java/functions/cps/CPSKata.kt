// Copyright © FunctionalHub.com 2018. All rights reserved.

package functions.cps

data class FailureException(override val message: String) : Exception(message)

fun performOperationCPS(check: () -> Boolean, onSuccess: (Int) -> Unit, onError: (Exception) -> Unit) {
    if (check()) onSuccess(42)
    else onError(FailureException("Error"))
}