package com.translate.presentation.main.fragment.worddetails

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.translate.R
import com.translate.data.entity.presentation.meaning.FullMeaningUIModel
import com.translate.presentation.base.BaseFragment
import com.translate.presentation.base.BaseViewModel
import kotlinx.android.synthetic.main.fragment_word_details.*

class WordDetailsFragment : BaseFragment() {

    private val viewModel: WordDetailsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(WordDetailsViewModel::class.java)
    }

    override fun getLayoutId(): Int = R.layout.fragment_word_details

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.meaningEvent.observe(viewLifecycleOwner, Observer(::handleMeaning))
        setHasOptionsMenu(true)
        getArgs()
    }

    override fun onResume() {
        super.onResume()
        setAppBarTitle(getString(R.string.details))
        lockDrawerLayout()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) viewModel.navigateBack()
        return false
    }

    private fun getArgs() {
        arguments
            ?.let { WordDetailsFragmentArgs.fromBundle(it).meaningId }
            ?.let { viewModel.getMeaning(it) }
    }

    private fun handleMeaning(fullMeaningUIModel: FullMeaningUIModel?) {
        fullMeaningUIModel?.run {
            f_wd_word_on_english.text = wordOnEng
            f_wd_word_translation.text = translation?.text
            f_wd_word_definition.text = definition?.text
            wordImgUrl?.let {
                Glide.with(this@WordDetailsFragment)
                    .load(getString(R.string.https, it.firstOrNull()))
                    .centerCrop()
                    .into(f_wd_img)
            }
        }
    }
}