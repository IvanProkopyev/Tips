package com.fintech.korona.tips.view.scanner

import com.fintech.korona.tips.view.BaseView

/**
 * @author Ivan Prokopyev
 */
interface ScannerView : BaseView {

    fun showScanErrorDialog()

    fun restartScanner()
}