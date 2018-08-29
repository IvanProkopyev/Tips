package com.fintech.korona.tips.data.repository.user

import com.fintech.korona.tips.data.datasource.InMemoryStorage
import com.fintech.korona.tips.data.datasource.UserDataSource

/**
 * @author Ivan Prokopyev
 */
interface UserRepositoryImpl {
    fun getUserId(): String

    fun putUserId(data: String)
}

class UserRepository : UserRepositoryImpl {

    private val dataSource: UserDataSource = InMemoryStorage()

    override fun getUserId(): String =
            dataSource.getUser()


    override fun putUserId(data: String) =
            dataSource.putUser(data)
}