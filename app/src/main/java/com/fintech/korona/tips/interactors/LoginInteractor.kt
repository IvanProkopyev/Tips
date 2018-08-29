package com.fintech.korona.tips.interactors

import io.reactivex.Completable

/**
 * @author Ivan Prokopyev
 */
class LoginInteractor {

    fun login(code: String): Completable =
            if (code == "1234") {
                Completable.complete()
            } else {
                Completable.error(Throwable())
            }

}