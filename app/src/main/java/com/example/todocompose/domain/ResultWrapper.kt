package com.example.todocompose.domain

/**
 * @author Dinesh Parmar
 * @see ResultWrapper is sealed class to wrap the result or error
 * @property E wrapping the Error on failed result
 * @property V wrapping the Value on success result
 */
sealed class ResultWrapper<out E, out V> {

    /**
     * @property V wrapping the Value on success data
     * @see Success data class extended from ResultWrapper to wrap the V type of data
     * @param V passing the result value
     */
    data class Success<out V>(val value: V) : ResultWrapper<Nothing, V>()

    /**
     * @property E wrapping the Error on failed data
     * @see Error data class extended from ResultWrapper to wrap the E type of errors
     * @param E passing the error value
     */
    data class Error<out E>(val error: E) : ResultWrapper<E, Nothing>()

    companion object Factory {

        /**
         * @param function passing the function as parameter to build result wrapper
         * @param V return the type of Result Data
         * @see build to build the result wrapper output
         */
        inline fun <V> build(
            function: () -> V): ResultWrapper<Exception, V> =
            try {
                Success(function.invoke())
            } catch (e: java.lang.Exception) {
                Error(e)
            }
    }
}