package com.translate.presentation.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.translate.R
import com.translate.presentation.base.BaseActivity
import com.translate.presentation.base.BaseViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getNavContainerId(): Int = R.id.a_main_nav_container

    override fun getViewModel(): BaseViewModel? = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(a_main_toolbar)
    }
}