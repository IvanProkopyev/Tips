package com.fintech.korona.tips.view.login

import com.fintech.korona.tips.view.BaseView

/**
 * @author Ivan Prokopyev
 */
interface LoginView : BaseView {

    fun showEmptyCodeMessage()

    fun showErrorLoginMessage()

    fun showLoginTip()
}