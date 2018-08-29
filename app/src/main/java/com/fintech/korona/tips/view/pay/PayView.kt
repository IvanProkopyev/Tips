package com.fintech.korona.tips.view.pay

import com.fintech.korona.tips.data.model.objects.ScanResponse
import com.fintech.korona.tips.view.BaseView
import java.math.BigDecimal

/**
 * @author Ivan Prokopyev
 */
interface PayView : BaseView {

    fun showLoadState()

    fun showSuccessState()

    fun showStatus(message: String)

    fun showErrorState(errorMessage: String)

    fun showData(data: ScanResponse)

    fun showTipAmount(amount: BigDecimal)

    fun showRetryButton()
}