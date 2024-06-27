package com.target.targetcasestudy

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import com.target.targetcasestudy.ui.list.DealListScreen
import com.target.targetcasestudy.ui.list.DealListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val dealListViewModel: DealListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            dealListViewModel.fetchDeals()
            setContent {
                val uiState by dealListViewModel.dealListUiStateFlow.collectAsState()
                val listUiState by dealListViewModel.dealUiStateFlow.collectAsState()
                DealListScreen(
                    uiState,
                    listUiState?.description
                ) {
                    (dealListViewModel::fetchDeal)(it)
                }
            }
        }
    }
}
