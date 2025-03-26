package com.it.excellent.architecture.domain.usecase

import android.os.Handler
import android.os.Looper
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class UseCaseThreadPoolScheduler : UseCaseScheduler {

    companion object {
        const val POOL_SIZE = 2
        const val MAC_POOL_SIZE = 4 * 2
        const val FIXED_POOL_SIZE = 4
        const val TIMEOUT = 30L
    }

    protected var mThreadPoolExecutor: ThreadPoolExecutor? = null
    private val mHandler = Handler(Looper.getMainLooper())

    init {
        mThreadPoolExecutor = ThreadPoolExecutor(FIXED_POOL_SIZE, FIXED_POOL_SIZE, TIMEOUT,
            TimeUnit.SECONDS, LinkedBlockingQueue())
    }

    override fun <V : UseCase.ResponseValue> onError(useCaseCallback: UseCase.UseCaseCallback<V>) {
        mHandler.post(useCaseCallback::onError)
    }

    override fun execute(runnable: Runnable) {
        mThreadPoolExecutor!!.execute(runnable)
    }

    override fun <V : UseCase.ResponseValue> notifyResponse(
        response: V,
        useCaseCallback: UseCase.UseCaseCallback<V>?
    ) {
        mHandler.post {
            useCaseCallback?.onSuccess(response)
        }
    }
}