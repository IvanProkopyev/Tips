package com.fintech.korona.tips.interactors

import com.fintech.korona.tips.data.repository.user.UserRepository
import com.fintech.korona.tips.data.repository.user.UserRepositoryImpl

/**
 * @author Ivan Prokopyev
 */
class UserInteractor {
    private val userRepository: UserRepositoryImpl = UserRepository()

    fun getData(): String {
        return userRepository.getUserId()
    }

    fun putData(data: String) {
        userRepository.putUserId(data)
    }
}