package com.it.excellent.architecture.domain.request

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

object AsyncTask {

    @JvmStatic
    fun <T> doIO(start: Action<T>): Observable<T> {
        return Observable.create(start::onEmit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> doCalculate(start: Action<T>): Observable<T> {
        return Observable.create(start::onEmit)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun interface Action<T> {

        fun onEmit(emitter: ObservableEmitter<T>)
    }

    interface Observer<T> : io.reactivex.Observer<T> {
        override fun onSubscribe(d: Disposable) {

        }

        override fun onNext(t: T & Any)

        override fun onError(e: Throwable) {

        }

        override fun onComplete() {

        }
    }
}