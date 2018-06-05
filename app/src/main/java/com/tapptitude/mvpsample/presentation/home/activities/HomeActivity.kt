package com.tapptitude.mvpsample.presentation.home.activities

import android.os.Bundle
import android.widget.Toast
import com.tapptitude.mvpsample.R
import com.tapptitude.mvpsample.presentation.common.BasePresenter
import com.tapptitude.mvpsample.presentation.common.activities.BaseActivity
import com.tapptitude.mvpsample.presentation.home.fragments.SampleFragment
import com.tapptitude.mvpsample.presentation.home.presenter.HomePresenter
import com.tapptitude.mvpsample.presentation.home.view.HomeView
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity<HomeView>(), HomeView {

    @Inject
    internal lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUpContent()
        presenter.loadIpAddress()
    }

    override fun getPresenter(): BasePresenter<HomeView> = presenter

    override fun onIpAddressLoaded(ipAddress: String) {
        titleTV.text = ipAddress
    }

    override fun onIpAddressLoadFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG)
                .show()
    }

    private fun setUpContent() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.containerFL, SampleFragment())
                .commit()
    }
}
