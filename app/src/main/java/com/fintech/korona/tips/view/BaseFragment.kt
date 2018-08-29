package com.fintech.korona.tips.view

import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * @author Ivan Prokopyev
 */
open class BaseFragment : Fragment() {

    open fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}