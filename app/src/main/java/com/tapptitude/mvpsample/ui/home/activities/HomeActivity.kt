package com.tapptitude.mvpsample.ui.home.activities

import android.os.Bundle
import android.widget.Toast
import com.tapptitude.mvpsample.R
import com.tapptitude.mvpsample.ui.common.BasePresenter
import com.tapptitude.mvpsample.ui.common.activities.BaseActivity
import com.tapptitude.mvpsample.ui.home.fragments.SampleFragment
import com.tapptitude.mvpsample.ui.home.presenter.HomePresenter
import com.tapptitude.mvpsample.ui.home.view.HomeView
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
