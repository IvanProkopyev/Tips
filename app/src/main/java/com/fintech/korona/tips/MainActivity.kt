package com.fintech.korona.tips

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.fintech.korona.tips.view.home.HomeFragment
import com.fintech.korona.tips.view.login.LoginFragment
import com.fintech.korona.tips.view.pay.PayFragment
import com.fintech.korona.tips.view.scanner.ScannerFragment


class MainActivity : AppCompatActivity(), AppRouter {

    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentManager = supportFragmentManager
        routeTo(LoginFragment.newInstance())
    }

    override fun onBackPressed() {
        val currentFragment = fragmentManager.findFragmentById(R.id.fragment_container)
        when (currentFragment) {
            is PayFragment -> routeTo(HomeFragment.newInstance())
            is HomeFragment -> {
                for (i in 1..fragmentManager.backStackEntryCount) {
                    fragmentManager.popBackStack()
                }
                finish()
            }
            else -> super.onBackPressed()
        }

    }

    override fun onResume() {
        super.onResume()
        val currentFragment = fragmentManager.findFragmentById(R.id.fragment_container)
        Log.i("fragment", currentFragment.toString())

        if (currentFragment !is ScannerFragment) {
            for (i in 1..fragmentManager.backStackEntryCount) {
                fragmentManager.popBackStack()
            }
            routeTo(LoginFragment.newInstance())
        }
    }

    override fun routeTo(fragment: Fragment) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}
