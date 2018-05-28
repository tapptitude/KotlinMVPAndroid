package com.tapptitude.mvpsample.ui.common

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<V : BaseView> {

    protected var view: V? = null
    private var disposables: CompositeDisposable? = null

    /**
     * The name of the method is given by the fact that it's ont only a setter for view,
     * it also inits other components.
     * @param view View implementation
     */
    open fun bind(view: V) {
        this.view = view
        initCompositeDisposable()
    }

    private fun initCompositeDisposable() {
        this.disposables = CompositeDisposable()
    }

    /**
     * The name of the method is given by the fact that beside destroying the view, it also disposes
     * the observers.
     */
    open fun unbind() {
        view = null
        disposeObservers()
    }

    private fun disposeObservers() {
        disposables?.dispose()
    }

    /**
     * An observer that disposes itself on [unbind]
     * @param D
     */
    protected open inner class SelfDisposingObserver<D>(private inline val onNextListener: (D) -> Unit = {},
                                                        private inline val onErrorListener: (Throwable) -> Unit = {})
        : Observer<D> {

        /**
         * This method handles the disposing mechanism for observers.
         * If overridden, do not forget to call .super
         */
        override fun onSubscribe(d: Disposable) {
            disposables?.add(d)
        }

        /**
         * This method can be overridden is necessary.
         */
        override fun onComplete() {

        }

        override fun onNext(t: D) {
            onNextListener.invoke(t)
        }

        override fun onError(e: Throwable) {
            onErrorListener.invoke(e)
        }
    }
}