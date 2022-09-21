package com.example.todocompose.domain.usecases

import com.example.todocompose.domain.ResultWrapper
import com.example.todocompose.domain.params.UseCaseParams

/**
 * @author Dinesh Parmar
 * @property UseCaseParams is an empty class to pass the arguments
 * @property Params is an Dynamic class extended by UseCaseParams for request of use-case
 * @property Result is an Dynamic class for response of use-case
 * */
abstract class BuildUseCase<Params : UseCaseParams, Result> {

    /**
     *@param params type of Params class to pass the argument on execute()
     * @see Throwable it throws the Exception of execution of execute()
     * @see Result it returns the type Data pass on extending @BuildUseCase
     * @see ResultWrapper returns the wrapper class to use-case
     */
    abstract suspend fun execute(parmas: Params): ResultWrapper<Throwable, Result>
}