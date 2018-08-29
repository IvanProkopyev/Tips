package com.fintech.korona.tips.presentation

import com.fintech.korona.tips.view.BaseView
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Ivan Prokopyev
 */
abstract class BasePresenter<V : BaseView> {

    protected val disposable: CompositeDisposable = CompositeDisposable()

    open fun onViewAttach() = Unit

    open fun onViewDetach() {
        disposable.dispose()
    }


}