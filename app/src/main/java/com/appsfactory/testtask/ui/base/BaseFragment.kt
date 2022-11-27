package com.appsfactory.testtask.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.viewbinding.ViewBinding
import com.appsfactory.testtask.utils.getValue
import com.appsfactory.testtask.utils.makeSnackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<
        ViewModelT : BaseViewModel,
        ViewBindingT : ViewBinding
        > : DaggerFragment() {

    @Inject
    internal open lateinit var viewModelFactory: Factory

    protected lateinit var viewModel: ViewModelT

    abstract val binding: ViewBindingT
    abstract val classType: Class<ViewModelT>

    override fun getDefaultViewModelProviderFactory(): Factory = viewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(classType)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initObserveSnackBar()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initObserveSnackBar() {
        viewModel.showSnackbar.observe(viewLifecycleOwner) {
            it.take()?.let { snackbarMessage ->
                binding.root.makeSnackbar(snackbarMessage.getValue(requireContext()), true).show()
            }
        }
    }
}