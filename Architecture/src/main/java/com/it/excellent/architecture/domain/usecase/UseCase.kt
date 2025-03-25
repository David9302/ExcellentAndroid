package com.it.excellent.architecture.domain.usecase

abstract class UseCase <Q : UseCase.RequestValues, P : UseCase.ResponseValue>(
    private var mRequestValues: Q,
    private var mUseCaseCallback: UseCaseCallback<P>
) {

    fun getRequestValues(): Q {
        return mRequestValues
    }

    fun setResponseValues(requestValues: Q) {
        mRequestValues = requestValues
    }

    fun getUseCaseCallback(): UseCaseCallback<P> {
        return mUseCaseCallback
    }

    fun setUseCaseCallback(useCaseCallback: UseCaseCallback<P>) {
        mUseCaseCallback = useCaseCallback
    }

    fun run() {
        executeUseCase(mRequestValues)
    }

    protected abstract fun executeUseCase(requestValues: Q)


    interface RequestValues {}

    interface ResponseValue {}

    interface UseCaseCallback<R> {

        fun onSuccess(response: R)

        fun onError() {}
    }
}