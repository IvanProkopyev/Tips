package com.fintech.korona.tips.view.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fintech.korona.tips.AppRouter
import com.fintech.korona.tips.R
import com.fintech.korona.tips.presentation.LoginPresenter
import com.fintech.korona.tips.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * @author Ivan Prokopyev
 */
class LoginFragment : BaseFragment(), LoginView {

    companion object {

        @JvmStatic
        fun newInstance() =
                LoginFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }

    private lateinit var presenter: LoginPresenter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = LoginPresenter(this, activity as AppRouter)
    }

    override fun onResume() {
        super.onResume()
        initListeners()
    }

    private fun initListeners() {
        enterButton.setOnClickListener { _ ->
            presenter.onLoginButtonClicked(enterCodeEditText.text.toString())
        }
    }

    override fun showEmptyCodeMessage() {
        Toast.makeText(context, "Введите код!", Toast.LENGTH_SHORT).show()
    }

    override fun showErrorLoginMessage() {
        Toast.makeText(context, "Неверный код!", Toast.LENGTH_SHORT).show()
    }

    override fun showLoginTip() {
        passwordHackTextView.visibility = View.VISIBLE
    }

    override fun onPause() {
        super.onPause()
        enterCodeEditText.setText("")
    }
}