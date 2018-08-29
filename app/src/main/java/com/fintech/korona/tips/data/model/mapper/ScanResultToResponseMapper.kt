package com.fintech.korona.tips.data.model.mapper

import com.fintech.korona.tips.data.model.objects.ScanResponse
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.zxing.Result
import io.reactivex.Single

/**
 * @author Ivan Prokopyev
 */
class ScanResultToResponseMapper {

    companion object {
        fun convert(from: Result): Single<ScanResponse> {
            return try {
                Single.just(Gson().fromJson(from.text, ScanResponse::class.java))
            } catch (e: JsonSyntaxException) {
                Single.error<ScanResponse>(e)
            }
        }
    }
}