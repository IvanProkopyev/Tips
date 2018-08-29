package com.fintech.korona.tips.presentation

import com.fintech.korona.tips.AppRouter
import com.fintech.korona.tips.interactors.LoginInteractor
import com.fintech.korona.tips.view.home.HomeFragment
import com.fintech.korona.tips.view.login.LoginView
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * @author Ivan Prokopyev
 */
class LoginPresenter(private val view: LoginView,
                     private val router: AppRouter) : BasePresenter<LoginView>() {

    private val interactor: LoginInteractor = LoginInteractor()

    init {

    }

    override fun onViewAttach() {
        super.onViewAttach()
    }

    override fun onViewDetach() {
        super.onViewDetach()
    }

    fun onLoginButtonClicked(code: String) {
        if (code.isEmpty()) {
            view.showEmptyCodeMessage()
        } else {
            interactor.login(code)
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { router.routeTo(HomeFragment.newInstance()) },
                            {
                                view.showErrorLoginMessage()
                                view.showLoginTip()
                            }
                    )
        }

    }
}