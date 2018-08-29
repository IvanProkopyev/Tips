package com.fintech.korona.tips.interactors

import com.fintech.korona.tips.data.model.objects.ScanResponse
import com.fintech.korona.tips.data.repository.order.OrderRepository
import com.fintech.korona.tips.data.repository.order.OrderRepositoryImpl

/**
 * @author Ivan Prokopyev
 */
class OrderInteractor {
    private val orderRepository: OrderRepository = OrderRepositoryImpl()

    fun getData(): ScanResponse {
        return orderRepository.getOrder()
    }

    fun putData(data: ScanResponse) {
        orderRepository.putOrder(data)
    }
}