package com.translate.presentation.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.navigation.NavigationView
import com.translate.R
import com.translate.common.Navigate
import com.translate.common.event.NetworkStateEvent
import com.translate.common.event.StateEvent
import com.translate.common.gone
import com.translate.common.visible
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var navController: NavController? = null
    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        if (getNavContainerId() != -1) navController =
            Navigation.findNavController(this, getNavContainerId())
        getViewModel()?.apply {
            val owner = this@BaseActivity
            networkConnectionGlobalEvent.observe(owner, Observer(::handleNetworkStateEvent))
            navigationGlobalEvent.observe(owner, { handleNavigation(it) })
        }
        getProgressViewModel()?.apply {
            progressGlobalEvent.observe(this@BaseActivity, Observer(::handleProgressBarEvent))
        }
    }

    override fun setSupportActionBar(toolbar: Toolbar?) {
        this.toolbar = toolbar
        this.toolbar?.setTitleTextAppearance(this, R.style.ToolbarText)
        super.setSupportActionBar(toolbar)
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    protected open fun getProgressLayout(): View? = null

    protected open fun getNavContainerId(): Int = -1

    protected open fun getDrawerToggle(): ActionBarDrawerToggle? = null

    fun getNavController(): NavController? = navController

    protected open fun getViewModel(): BaseViewModel? = null

    private fun getProgressViewModel(): BaseProgressViewModel? =
        getViewModel() as? BaseProgressViewModel?

    protected open fun getNavigationItemSelectedListener(): NavigationView.OnNavigationItemSelectedListener? =
        null

    protected open fun getNavigationView(): NavigationView? = null

    protected open fun getRootLayout(): ViewGroup? = null

    open fun lockDrawerLayout() {
        setBackButton(true)
    }

    open fun unlockDrawerLayout() {
        setBackButton(false)
    }

    fun hideNavigationIcon() {
        toolbar?.navigationIcon = null
    }

    open fun showProgressBar() {
        getProgressLayout()?.visible()
    }

    open fun hideProgressBar() {
        getProgressLayout()?.gone()
    }

    open fun showNetworkConnection() {

    }

    open fun showNetworkErrorDialog() {

    }

    open fun hideNetworkConnection() {

    }

    protected fun notImplementedToast() {
        Toast.makeText(this, R.string.not_implemented, Toast.LENGTH_SHORT).show()
    }

    fun showErrorToast(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    fun showAppBar() {
        supportActionBar?.show()
    }

    fun hideAppBar() {
        supportActionBar?.hide()
    }

    fun setBackButton(isEnabled: Boolean) {
        if (isEnabled) showAppBar()
        supportActionBar?.setDisplayHomeAsUpEnabled(isEnabled)
        supportActionBar?.setDisplayShowHomeEnabled(isEnabled)
        getDrawerToggle()?.syncState()
        if (!isEnabled) toolbar?.setNavigationIcon(R.drawable.ic_menu)
        else supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
    }

    fun setAppBarTitle(@StringRes titleResId: Int) {
        supportActionBar?.setTitle(titleResId)
    }

    fun setAppBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun handleProgressBarEvent(event: StateEvent) {
        when (event) {
            StateEvent.SHOW -> showProgressBar()
            else -> hideProgressBar()
        }
    }

    fun handleNetworkStateEvent(event: NetworkStateEvent) {
        when (event) {
            NetworkStateEvent.CONNECT -> showNetworkConnection()
            NetworkStateEvent.ERROR -> showNetworkErrorDialog()
            else -> hideNetworkConnection()
        }
    }

    open fun handleNavigation(event: Navigate.Global) {
        notImplementedToast()
    }
}