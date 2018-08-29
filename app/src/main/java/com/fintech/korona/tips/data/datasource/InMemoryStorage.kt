package com.fintech.korona.tips.data.datasource

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.fintech.korona.tips.Tips
import com.fintech.korona.tips.data.datasource.crypto.Cryptography.Companion.decrypt
import com.fintech.korona.tips.data.datasource.crypto.Cryptography.Companion.encrypt
import com.fintech.korona.tips.data.model.objects.Card
import com.fintech.korona.tips.data.model.objects.ScanResponse
import com.google.gson.Gson
import java.util.*

/**
 * @author Ivan Prokopyev
 */

class InMemoryStorage : OrderDataSource, UserDataSource, CardDataSource {

    private val preferences: SharedPreferences =
            Tips.provideContext().getSharedPreferences("APP_DATA", MODE_PRIVATE)
    private val gson: Gson = Gson()

    companion object {
        private const val SCAN_RESPONSE = "SCAN_RESPONSE"
        private const val USER_INFO = "USER_INFO"
        private const val CARD = "CARD"
    }

    override fun putScanResponse(data: ScanResponse) {
        val dataString = gson.toJson(data)
        preferences.edit()
                .putString(encrypt(SCAN_RESPONSE), encrypt(dataString))
                .apply()

    }

    override fun getScanResponse(): ScanResponse {
        val dataString = decrypt(preferences.getString(encrypt(SCAN_RESPONSE), ""))
        return if (dataString == "") {
            ScanResponse()
        } else {
            gson.fromJson(dataString, ScanResponse::class.java)
        }
    }

    override fun putUser(data: String) {

        preferences.edit()
                .putString(encrypt(USER_INFO), encrypt(data))
                .apply()
    }

    override fun getUser(): String {
        val userId = decrypt(preferences.getString(encrypt(USER_INFO), ""))
        return if (userId == "") {
            val newId = UUID.randomUUID().toString()
            putUser(newId)
            newId
        } else userId

    }

    override fun putCard(data: Card) {
        val dataString = gson.toJson(data)

        preferences.edit()
                .putString(encrypt(CARD), encrypt(dataString))
                .apply()

    }

    override fun getCard(): Card {
        val dataString = decrypt(preferences.getString(encrypt(CARD), ""))
        return if (dataString == "") {
            Card("", "", "")
        } else {
            gson.fromJson(dataString, Card::class.java)
        }
    }

}
