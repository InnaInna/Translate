package com.translate.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import com.translate.R
import com.translate.common.Navigate
import com.translate.common.event.NetworkStateEvent
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment: DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getLayoutId(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel().apply {
            navigationGlobalEvent.observe(viewLifecycleOwner, Observer(::handleNavigation))
            networkConnectionGlobalEvent.observe(viewLifecycleOwner, Observer(::handleNetworkStateEvent))
            errorEvent.observe(viewLifecycleOwner, Observer(::showErrorToast))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = super.onOptionsItemSelected(item)

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): BaseViewModel

    private fun getBaseActivity(): BaseActivity? =
        if (activity is BaseActivity) activity as BaseActivity else null

    protected fun notImplementedToast() {
        activity?.let { Toast.makeText(it, R.string.not_implemented, Toast.LENGTH_SHORT).show() }
    }

    private fun handleNavigation(event: Navigate.Global) {
        when (event) {
            Navigate.Global.Back -> navigateUp()
            else -> getBaseActivity()?.handleNavigation(event)
        }
    }

    private fun handleNetworkStateEvent(networkStateEvent: NetworkStateEvent) {
        getBaseActivity()?.handleNetworkStateEvent(networkStateEvent)
    }

    fun navigate(id: Int, bundle: Bundle? = null) {
        val navController = getBaseActivity()?.getNavController()
        val action = navController?.currentDestination?.getAction(id)
        if (action != null) navController.navigate(id, bundle)
    }

    fun navigate(directions: NavDirections) {
        getBaseActivity()?.getNavController()?.navigate(directions)
    }

    fun navigateUp() {
        getBaseActivity()?.getNavController()?.navigateUp()
    }

    fun showErrorToast(error: String?) {
        val errorText = error?.let { it } ?: getString(R.string.sth_went_wrong)
        Toast.makeText(context, errorText, Toast.LENGTH_SHORT).show()
    }

    protected fun showAppBar() {
        getBaseActivity()?.showAppBar()
    }

    protected fun hideAppBar() {
        getBaseActivity()?.hideAppBar()
    }

    protected fun setAppBarTitle(@StringRes titleResId: Int) {
        getBaseActivity()?.setAppBarTitle(titleResId)
    }

    protected fun setAppBarTitle(title: String) {
        getBaseActivity()?.setAppBarTitle(title)
    }

    protected fun lockDrawerLayout() {
        getBaseActivity()?.lockDrawerLayout()
    }

    protected fun unlockDrawerLayout() {
        getBaseActivity()?.unlockDrawerLayout()
    }

    protected fun hideNavigationIcon() {
        getBaseActivity()?.hideNavigationIcon()
    }
}