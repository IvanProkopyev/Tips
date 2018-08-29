package com.fintech.korona.tips.view.home

import android.Manifest.permission.CAMERA
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fintech.korona.tips.AppRouter
import com.fintech.korona.tips.R
import com.fintech.korona.tips.data.model.objects.Card
import com.fintech.korona.tips.presentation.HomePresenter
import com.fintech.korona.tips.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_card.*


/**
 * @author Ivan Prokopyev
 */
class HomeFragment : BaseFragment(), HomeView {

    companion object {

        private const val CAMERA_REQUEST_CODE = 1

        @JvmStatic
        fun newInstance() =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }

    private lateinit var presenter: HomePresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = HomePresenter(this, activity as AppRouter)
        setListeners()
        presenter.onViewAttach()
    }

    private fun setListeners() {
        cardNumberEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //do nothing
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //do nothing

            }

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    if (it.length == 4 || it.length == 9 || it.length == 14) {
                        cardNumberEditText
                                .setText(s.toString() + " ", TextView.BufferType.EDITABLE)
                        cardNumberEditText.setSelection(it.length + 1)
                    }
                }

            }
        })

        cardDateEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //do nothing
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //do nothing

            }

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    if (it.length == 2 && count != 0) {
                        cardDateEditText
                                .setText(s.toString() + "/", TextView.BufferType.EDITABLE)
                        cardDateEditText.setSelection(it.length + 1)
                    }
                }

            }
        })

        saveCardButton.setOnClickListener { _ ->
            presenter.onSaveCardsClick(
                    cardNumberEditText.text.toString(),
                    cardHolderEditText.text.toString(),
                    cardDateEditText.text.toString(),
                    cardCvEditText.text.toString())
        }

        scanButton.setOnClickListener { _ ->
            if (checkPermission()) {
                onScanEventReceive()
            } else {
                requestPermissions()
            }
        }
    }

    private fun onScanEventReceive() {
        if (cardNumberEditText.text.toString() != "" &&
                cardHolderEditText.text.toString() != "" &&
                cardDateEditText.text.toString() != "" &&
                cardCvEditText.text.toString() != "") {
            presenter.onScanButtonClick()
        } else {
            showMessage("Введите данные карты!")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun showCard(userCard: Card) {
        cardNumberEditText.setText(userCard.number, TextView.BufferType.EDITABLE)
        cardHolderEditText.setText(userCard.holderName, TextView.BufferType.EDITABLE)
        cardDateEditText.setText(userCard.date, TextView.BufferType.EDITABLE)
    }

    private fun checkPermission(): Boolean =
            ContextCompat.checkSelfPermission(context, CAMERA) == PackageManager.PERMISSION_GRANTED


    override fun requestPermissions() =
            requestPermissions(arrayOf(CAMERA), CAMERA_REQUEST_CODE)

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        if (requestCode == CAMERA_REQUEST_CODE && grantResults.isNotEmpty()) {
            val cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED

            if (!cameraAccepted) {
                showMessage("Разрешение не получено")

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (shouldShowRequestPermissionRationale(CAMERA)) {
                        requestPermissions(arrayOf(CAMERA), CAMERA_REQUEST_CODE)
                        return
                    }
                }
            } else {
                onScanEventReceive()
            }
        }

    }
}