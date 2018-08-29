package com.fintech.korona.tips.interactors

import com.fintech.korona.tips.data.model.mapper.ScanResultToResponseMapper
import com.fintech.korona.tips.data.model.objects.ScanResponse
import com.google.zxing.Result
import io.reactivex.Completable

/**
 * @author Ivan Prokopyev
 */
class ScanInteractor {

    private val orderInteractor: OrderInteractor = OrderInteractor()

    fun proceedScanResult(scanResult: Result): Completable =
            ScanResultToResponseMapper.convert(scanResult)
                    .flatMapCompletable {
                        Completable.fromAction { saveOrder(it) }
                    }


    private fun saveOrder(response: ScanResponse) {
        orderInteractor.putData(response)
    }
}