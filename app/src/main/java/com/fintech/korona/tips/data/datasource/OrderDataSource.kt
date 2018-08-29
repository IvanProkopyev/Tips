package com.fintech.korona.tips.data.datasource

import com.fintech.korona.tips.data.model.objects.ScanResponse

/**
 * @author Ivan Prokopyev
 */
interface OrderDataSource {

    fun putScanResponse(data: ScanResponse)

    fun getScanResponse(): ScanResponse
}