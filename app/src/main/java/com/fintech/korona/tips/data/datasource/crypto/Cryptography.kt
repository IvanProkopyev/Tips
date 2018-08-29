package com.fintech.korona.tips.data.datasource.crypto

import android.util.Base64

/**
 * @author Ivan Prokopyev
 */
class Cryptography {
    companion object {

        fun encrypt(input: String): String {
            return Base64.encodeToString(input.toByteArray(), Base64.DEFAULT)
        }

        fun decrypt(input: String): String {
            return String(Base64.decode(input, Base64.DEFAULT))
        }
    }
}