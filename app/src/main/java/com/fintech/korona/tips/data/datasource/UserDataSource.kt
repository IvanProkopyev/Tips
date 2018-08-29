package com.fintech.korona.tips.data.datasource

/**
 * @author Ivan Prokopyev
 */
interface UserDataSource {

    fun putUser(data: String)

    fun getUser(): String
}