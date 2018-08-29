package com.fintech.korona.tips

import android.app.Application
import android.content.Context


/**
 * @author Ivan Prokopyev
 */
class Tips : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: Tips? = null

        fun provideContext(): Context {
            return instance!!.applicationContext
        }

    }

}