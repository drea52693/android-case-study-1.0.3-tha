package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels

@AndroidEntryPoint
class DealListFragment : Fragment() {

    private val dealListViewModel: DealListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dealListViewModel.fetchDeals()
        return ComposeView(requireContext()).apply {
            setContent {
                DealListScreen(
                    dealListViewModel.dealListUiStateFlow.collectAsState().value,
                    dealListViewModel.dealUiStateFlow.collectAsState().value?.description
                ) {
                    (dealListViewModel::fetchDeal)(it)
                }
            }
        }
    }
}
