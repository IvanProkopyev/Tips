package com.fintech.korona.tips.presentation

import com.fintech.korona.tips.AppRouter
import com.fintech.korona.tips.data.model.objects.ScanResponse
import com.fintech.korona.tips.interactors.OrderInteractor
import com.fintech.korona.tips.interactors.PayInteractor
import com.fintech.korona.tips.view.pay.PayView
import com.fintech.korona.tips.view.scanner.ScannerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.math.BigDecimal

/**
 * @author Ivan Prokopyev
 */
class PayPresenter(private val view: PayView,
                   private val router: AppRouter) : BasePresenter<PayView>() {

    private val interactor: PayInteractor = PayInteractor()
    private val orderInteractor: OrderInteractor = OrderInteractor()

    private lateinit var data: ScanResponse

    override fun onViewAttach() {
        super.onViewAttach()
        loadData()
    }

    private fun loadData() {
        data = orderInteractor.getData()
        view.showData(data)
        requestPreferredTips()

    }

    fun onPayClick(payWithTip: Boolean, tipsAmount: BigDecimal?) {
        interactor.payOrder()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { view.showLoadState() }
                .subscribe(
                        {
                            if (payWithTip && tipsAmount != null) {
                                payTips(tipsAmount)
                            } else {
                                view.showStatus("Заказ оплачен!")
                                view.showSuccessState()
                            }
                        },
                        { e ->
                            view.showErrorState(e.toString())
                            view.showStatus("Заказ не оплачен!")
                            view.showRetryButton()
                        }
                )
    }

    fun onBackPressed() {
        router.routeTo(ScannerFragment.newInstance())
    }

    private fun payTips(tipsAmount: BigDecimal) {
        interactor.payTips(tipsAmount)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            view.showSuccessState()
                            view.showStatus("Заказ оплачен с чаевами!")
                        },
                        { e -> view.showErrorState(e.toString())
                            view.showStatus("Чаевые не оплачены!")
                        }
                )
    }

    private fun requestPreferredTips() {
        interactor.requestTip()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ view.showTipAmount(it) }, {})

    }
}