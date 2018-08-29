package com.fintech.korona.tips

import android.support.v4.app.Fragment

/**
 * @author Ivan Prokopyev
 */
interface AppRouter {

    fun routeTo(fragment: Fragment)
}