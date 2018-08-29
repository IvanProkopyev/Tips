package com.fintech.korona.tips.interactors

import com.fintech.korona.tips.data.model.objects.KoronaResponse
import com.fintech.korona.tips.data.repository.order.OrderRepositoryImpl
import com.fintech.korona.tips.data.repository.tip.TipResponseImpl
import io.reactivex.Maybe
import io.reactivex.Single
import java.math.BigDecimal

/**
 * @author Ivan Prokopyev
 */

class PayInteractor {
    private val userInteractor = UserInteractor()

    private val orderRepository = OrderRepositoryImpl()
    private val tipRepository = TipResponseImpl()

    fun payOrder(): Single<KoronaResponse> {
        val userId = userInteractor.getData()
        return orderRepository.payOrder(userId)
    }

    fun requestTip(): Maybe<BigDecimal> {
        return Maybe.just(BigDecimal(50))   //mocked
    }

    fun payTips(amount: BigDecimal): Single<KoronaResponse> =
            tipRepository.payTips(amount)
}