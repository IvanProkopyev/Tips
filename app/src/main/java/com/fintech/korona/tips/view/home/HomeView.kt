package com.fintech.korona.tips.view.home

import com.fintech.korona.tips.data.model.objects.Card
import com.fintech.korona.tips.view.BaseView

/**
 * @author Ivan Prokopyev
 */
interface HomeView : BaseView {
    fun requestPermissions()

    fun showCard(userCard: Card)

    fun showMessage(message: String)
}