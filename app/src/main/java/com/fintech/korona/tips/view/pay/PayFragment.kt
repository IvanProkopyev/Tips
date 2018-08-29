package com.fintech.korona.tips.view.pay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fintech.korona.tips.AppRouter
import com.fintech.korona.tips.R
import com.fintech.korona.tips.data.model.objects.ScanResponse
import com.fintech.korona.tips.presentation.PayPresenter
import com.fintech.korona.tips.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_pay.*
import java.math.BigDecimal

class PayFragment : BaseFragment(), PayView {

    companion object {

        @JvmStatic
        fun newInstance() =
                PayFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }

    private lateinit var presenter: PayPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pay, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = PayPresenter(this, activity as AppRouter)

        presenter.onViewAttach()
    }

    override fun onResume() {
        super.onResume()
        initListeners()
    }

    private fun initListeners() {
        payButton.setOnClickListener {
            val payWithTip = payWithTipsCheckBox.isChecked
            presenter.onPayClick(payWithTip, BigDecimal(tipsAmountEditText.text.toString()))
        }
    }

    override fun showRetryButton() {
        payButton.text = "Повторить попытку"
    }

    override fun showData(data: ScanResponse) {
        orderAmountTextView.text = data.orderAmount.toPlainString()
        nameTextView.text = data.waiterId.toString()
    }

    override fun showLoadState() {
        payProgressBar.visibility = View.VISIBLE
        payButton.isEnabled = false
    }

    private fun showContentState() {
        payProgressBar.visibility = View.GONE
        payButton.isEnabled = true
    }

    override fun showTipAmount(amount: BigDecimal) {
        tipsAmountEditText.setText(amount.toPlainString())
    }

    override fun showStatus(message: String) {
        statusTextView.text = message
    }

    override fun showSuccessState() {
        showContentState()
        paymentStateImageView.setImageResource(R.drawable.ic_success)
        payButton.visibility = View.INVISIBLE
        payWithTipsCheckBox.visibility = View.INVISIBLE
    }

    override fun showErrorState(errorMessage: String) {
        showContentState()
        paymentStateImageView.setImageResource(R.drawable.ic_error)
        showMessage(errorMessage)
    }


}
