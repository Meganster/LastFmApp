package com.appsfactory.testtask.ui.base.compose

import android.os.Bundle
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.navigation.fragment.findNavController
import com.appsfactory.testtask.utils.getValue
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseComposeFragment<
        ViewModelT : BaseComposeViewModel
        > : DaggerFragment() {

    @Inject
    internal open lateinit var viewModelFactory: Factory

    protected lateinit var viewModel: ViewModelT

    abstract val classType: Class<ViewModelT>

    override fun getDefaultViewModelProviderFactory(): Factory = viewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(classType)
    }

    @Composable
    protected fun initSnackbarObserver(snackbarHostState: SnackbarHostState) {
        viewModel.showSnackbar.Handler { snackBar ->
            launch {
                snackbarHostState.showSnackbar(snackBar.getValue(requireContext()))
            }
        }
    }

    fun navController() = findNavController()
}