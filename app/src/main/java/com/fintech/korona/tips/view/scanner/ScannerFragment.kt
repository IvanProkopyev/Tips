package com.fintech.korona.tips.view.scanner

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fintech.korona.tips.AppRouter
import com.fintech.korona.tips.R
import com.fintech.korona.tips.presentation.ScannerPresenter
import com.fintech.korona.tips.view.BaseFragment
import com.google.zxing.Result
import kotlinx.android.synthetic.main.fragment_scanner.*
import me.dm7.barcodescanner.zxing.ZXingScannerView


/**
 * @author Ivan Prokopyev
 */
class ScannerFragment : BaseFragment(), ZXingScannerView.ResultHandler, ScannerView {

    companion object {

        @JvmStatic
        fun newInstance() =
                ScannerFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }

    private lateinit var presenter: ScannerPresenter
    private lateinit var scannerView: ZXingScannerView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = ScannerPresenter(this, activity as AppRouter)
        initScanner()
    }

    private fun initScanner() {
        scannerView = ZXingScannerView(context)
        scannerView.setResultHandler(this)
        scannerView.startCamera()
        scannerFrame.addView(scannerView)
    }

    override fun handleResult(scanResult: Result) {
        Log.i("12313", scanResult.text)
        presenter.onScanResultReceive(scanResult)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scanner, container, false)
    }

    override fun showScanErrorDialog() {
        val builder: AlertDialog.Builder =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert)
                } else {
                    AlertDialog.Builder(context)
                }
        builder.setTitle("Ошибка!")
                .setMessage("Повторить попытку?")
                .setPositiveButton(android.R.string.yes) { _, _ ->
                    presenter.onPositiveScanClicked()
                }
                .setNegativeButton(android.R.string.no) { _, _ ->
                    presenter.onNegativeScanClicked()
                }
                .show()
    }

    override fun restartScanner() {
        scannerView.stopCamera()
        scannerView.setResultHandler(this)
        scannerView.startCamera()
    }


}