package com.translate.presentation.main.fragment.translate

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.translate.R
import com.translate.common.AndroidUtils
import com.translate.common.gone
import com.translate.common.visible
import com.translate.data.entity.presentation.translate.MeaningUIModel
import com.translate.presentation.base.BaseFragment
import com.translate.presentation.base.BaseViewModel
import com.translate.presentation.base.adapter.listeners.OnItemClickListener
import com.translate.presentation.base.adapter.listeners.PaginationListener
import com.translate.presentation.main.fragment.translate.adapter.TranslateAdapter
import kotlinx.android.synthetic.main.fragment_translate.*


class TranslateFragment : BaseFragment() {

    private val viewModel: TranslateViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(TranslateViewModel::class.java)
    }

    private val adapter: TranslateAdapter by lazy { TranslateAdapter(itemListener) }
    private val itemListener: OnItemClickListener<MeaningUIModel>
        get() = object : OnItemClickListener<MeaningUIModel> {
            override fun onItemClick(item: MeaningUIModel) {
                val direction =
                    TranslateFragmentDirections.actionTranslateFragmentToWordDetailsFragment(item.id.toString())
                navigate(direction)
            }
        }

    override fun getLayoutId(): Int = R.layout.fragment_translate

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            initItems.observe(viewLifecycleOwner, Observer(::handleInitItems))
            loadMoreItems.observe(viewLifecycleOwner, Observer(adapter::addItems))
        }
        setHasOptionsMenu(true)
        initView()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView: SearchView? = searchItem?.actionView as SearchView

        val text =
            searchView?.findViewById<View>(androidx.appcompat.R.id.search_src_text) as EditText
        val searchCloseIcon: ImageView =
            searchView.findViewById<View>(androidx.appcompat.R.id.search_close_btn) as ImageView

        activity?.let {
            text.setTextColor(ContextCompat.getColor(it, R.color.white))
            text.setHintTextColor(ContextCompat.getColor(it, R.color.gray))
            val magHint = SpannableStringBuilder("  ")
            magHint.append(getString(R.string.search))
            text.hint = magHint
            searchCloseIcon.setImageDrawable(ContextCompat.getDrawable(it, R.drawable.ic_close))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView? = searchItem?.actionView as SearchView
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                AndroidUtils.hideKeyboard(activity)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.search(it) }
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onResume() {
        super.onResume()
        showAppBar()
        setAppBarTitle(getString(R.string.app_name))
        hideNavigationIcon()
    }

    override fun onPause() {
        super.onPause()
        AndroidUtils.hideKeyboard(activity)
    }

    private fun initView() {
        f_translate_rv.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = this@TranslateFragment.adapter
            addOnScrollListener(object : PaginationListener() {
                override fun loadMoreItems() = viewModel.loadNextPage()

                override fun isLastPage(): Boolean = viewModel.isLastPage

                override fun isLoading(): Boolean = viewModel.isLoading
            })
        }
    }

    private fun handleInitItems(list: List<MeaningUIModel>) {
        when (list.isNullOrEmpty()) {
            true -> showEmptySate()
            false -> setData(list)
        }
    }

    private fun showEmptySate() {
        f_translate_rv.gone()
        f_translate_empty_state_tv.visible()
    }

    private fun hideEmptySate() {
        f_translate_rv.visible()
        f_translate_empty_state_tv.gone()
    }

    private fun setData(list: List<MeaningUIModel>) {
        hideEmptySate()
        adapter.setItems(list)
    }
}