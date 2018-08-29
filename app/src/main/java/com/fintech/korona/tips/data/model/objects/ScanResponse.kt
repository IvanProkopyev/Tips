package com.fintech.korona.tips.data.model.objects

import java.math.BigDecimal

/**
 * @author Ivan Prokopyev
 */
data class ScanResponse(val orderAmount: BigDecimal = BigDecimal.ZERO,
                        val waiterId: Int = 0,
                        val legalAddress: String = "")