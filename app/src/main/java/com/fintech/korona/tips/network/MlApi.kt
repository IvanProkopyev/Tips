package com.fintech.korona.tips.network

import io.reactivex.Single
import retrofit2.http.GET
import java.math.BigDecimal

/**
 * @author Ivan Prokopyev
 */
interface MlApi {

    @GET("/tip_preffered")
    fun getPrefferedTipAmount(): Single<BigDecimal>

}