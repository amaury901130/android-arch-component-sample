package com.rootstrap.samples.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.rootstrap.samples.R
import com.rootstrap.samples.databinding.ActivityMainBinding
import com.rootstrap.samples.ui.view.MainActivityView
import com.rootstrap.samples.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private var mainBinding: ActivityMainBinding? = null
    private var viewModel: MainActivityViewModel? = null
    private var view: MainActivityView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        view = MainActivityView(mainBinding!!, viewModel, this)
    }
}
