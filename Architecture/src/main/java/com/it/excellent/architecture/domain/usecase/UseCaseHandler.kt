package com.it.excellent.architecture.domain.usecase

class UseCaseHandler public constructor(
    private val mUseCaseScheduler: UseCaseScheduler
) {
    companion object {
        private var INSTANCE: UseCaseHandler? = null

        fun getInstance(): UseCaseHandler = INSTANCE ?: synchronized(this) {
            INSTANCE ?: UseCaseHandler(UseCaseThreadPoolScheduler()).also { INSTANCE = it }
        }
    }

    fun <T : UseCase.RequestValues, R : UseCase.ResponseValue> execute(
        useCase: UseCase<T, R>,
        values: T,
        callback: UseCase.UseCaseCallback<R>
    ) {
        useCase.run {
            mRequestValues = values
            mUseCaseCallback = UICallbackWrapper(callback)
        }
        mUseCaseScheduler.execute(useCase::run)
    }

    private fun <V : UseCase.ResponseValue> notifyResponse(
        response: V,
        callback: UseCase.UseCaseCallback<V>
    ) = mUseCaseScheduler.notifyResponse(response, callback)

    private fun <V : UseCase.ResponseValue> notifyError(
        callback: UseCase.UseCaseCallback<V>
    ) = mUseCaseScheduler.onError(callback)

    /**
     * 刷新.
     */
    private inner class UICallbackWrapper<V : UseCase.ResponseValue>(
        private val callback: UseCase.UseCaseCallback<V>
    ) : UseCase.UseCaseCallback<V>{
        override fun onSuccess(response: V) = notifyResponse(response, callback)
        override fun onError() = notifyError(callback)
    }
}