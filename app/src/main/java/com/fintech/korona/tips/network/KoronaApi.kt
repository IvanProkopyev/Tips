package com.fintech.korona.tips.network

import com.fintech.korona.tips.data.model.objects.KoronaResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.math.BigDecimal

/**
 * @author Ivan Prokopyev
 */
interface KoronaApi {

    @FormUrlEncoded
    @POST("/pay/main_pay")
    fun payOrder(@Field("userId") userId: String,
                 @Field("restaurantId") restaurantId: String,
                 @Field("sum") sum: BigDecimal): Single<KoronaResponse>

    @FormUrlEncoded
    @POST("/pay/tip_pay")
    fun payTip(@Field("userId") userId: String,
               @Field("waiter_id") orderId: Int,
               @Field("sum") sum: BigDecimal): Single<KoronaResponse>

    companion object Factory {

        fun create(): KoronaApi =
                Retrofit.Builder()
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("http://178.62.2.36:8081")
                        .build()
                        .create(KoronaApi::class.java)


    }

}