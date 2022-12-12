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
        viewModel.showSnackbar.Handler { snackbar ->
            launch {
                val snackbarResult = snackbarHostState.showSnackbar(
                    message = snackbar.text.getValue(requireContext()),
                    actionLabel = snackbar.buttonTitle?.getValue(requireContext())
                )

                snackbar.onSnackbarAction?.invoke(snackbarResult)
            }
        }
    }

    fun navController() = findNavController()
}