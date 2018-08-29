package com.fintech.korona.tips.presentation

import com.fintech.korona.tips.AppRouter
import com.fintech.korona.tips.interactors.ScanInteractor
import com.fintech.korona.tips.view.home.HomeFragment
import com.fintech.korona.tips.view.pay.PayFragment
import com.fintech.korona.tips.view.scanner.ScannerView
import com.google.zxing.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Ivan Prokopyev
 */
class ScannerPresenter(private val view: ScannerView,
                       private val router: AppRouter) : BasePresenter<ScannerView>() {

    private val interactor: ScanInteractor = ScanInteractor()

    fun onScanResultReceive(scanResult: Result) {
        interactor.proceedScanResult(scanResult)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { router.routeTo(PayFragment.newInstance()) },
                        { view.showScanErrorDialog() }
                )
    }

    fun onPositiveScanClicked() {
        view.restartScanner()
    }

    fun onNegativeScanClicked() {
        router.routeTo(HomeFragment.newInstance())
    }

}
