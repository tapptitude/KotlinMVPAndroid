package com.tapptitude.mvpsample.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tapptitude.mvpsample.R
import com.tapptitude.mvpsample.data.network.models.DateTime
import com.tapptitude.mvpsample.ui.common.BasePresenter
import com.tapptitude.mvpsample.ui.common.fragments.BaseFragment
import com.tapptitude.mvpsample.ui.home.presenter.SamplePresenter
import com.tapptitude.mvpsample.ui.home.view.SampleView
import kotlinx.android.synthetic.main.fragment_sample.*
import javax.inject.Inject

class SampleFragment : BaseFragment<SampleView>(), SampleView {

    @Inject
    internal lateinit var presenter: SamplePresenter

    override fun getPresenter(): BasePresenter<SampleView> = presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_sample, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadDateTime()
    }

    override fun onDateTimeLoaded(dateTime: DateTime) {
        timeTV.text = dateTime.time
        dateTV.text = dateTime.date
    }
}
