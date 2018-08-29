package com.fintech.korona.tips.data.repository.tip

import com.fintech.korona.tips.data.datasource.InMemoryStorage
import com.fintech.korona.tips.data.datasource.OrderDataSource
import com.fintech.korona.tips.data.datasource.UserDataSource
import com.fintech.korona.tips.data.model.objects.KoronaResponse
import com.fintech.korona.tips.network.KoronaApi
import io.reactivex.Single
import java.math.BigDecimal

/**
 * @author Ivan Prokopyev
 */
interface TipRepository {

    fun payTips(amount: BigDecimal): Single<KoronaResponse>
}

class TipResponseImpl : TipRepository {
    private val orderDataSource: OrderDataSource = InMemoryStorage()
    private val userDataSource: UserDataSource = InMemoryStorage()


    override fun payTips(amount: BigDecimal): Single<KoronaResponse> {
        return KoronaApi.create()
                .payTip(userDataSource.getUser(),
                        orderDataSource.getScanResponse().waiterId,
                        amount)
    }

}