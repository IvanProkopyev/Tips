package com.fintech.korona.tips.data.repository.order

import com.fintech.korona.tips.data.datasource.InMemoryStorage
import com.fintech.korona.tips.data.datasource.OrderDataSource
import com.fintech.korona.tips.data.model.objects.KoronaResponse
import com.fintech.korona.tips.data.model.objects.ScanResponse
import com.fintech.korona.tips.network.KoronaApi
import io.reactivex.Single

/**
 * @author Ivan Prokopyev
 */
interface OrderRepository {
    fun getOrder(): ScanResponse

    fun putOrder(data: ScanResponse)

    fun payOrder(userId: String): Single<KoronaResponse>
}

class OrderRepositoryImpl : OrderRepository {

    private val orderDataSource: OrderDataSource = InMemoryStorage()

    override fun payOrder(userId: String): Single<KoronaResponse> {
        val data = getOrder()
        return KoronaApi.create()
                .payOrder(userId, data.legalAddress, data.orderAmount)
    }

    override fun getOrder(): ScanResponse =
            orderDataSource.getScanResponse()


    override fun putOrder(data: ScanResponse) =
            orderDataSource.putScanResponse(data)
}