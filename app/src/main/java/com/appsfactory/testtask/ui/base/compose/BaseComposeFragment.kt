package com.appsfactory.testtask.ui.base.compose

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.appsfactory.testtask.utils.getValue
import kotlinx.coroutines.launch

abstract class BaseComposeFragment : Fragment() {

    abstract val viewModel: BaseComposeViewModel

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