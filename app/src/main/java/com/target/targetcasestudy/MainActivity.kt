package com.target.targetcasestudy

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.target.targetcasestudy.ui.list.DealListViewModel
import com.target.targetcasestudy.ui.details.ProductDetailsScreen
import com.target.targetcasestudy.ui.details.ProductDetailsViewModel
import com.target.targetcasestudy.ui.list.DealListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: DealListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val dealList by viewModel.dealListUiStateFlow.collectAsState()
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "list") {
                    composable("list") {
                        DealListScreen(dealList) {
                            navController.navigate("details/$it")
                        }
                    }
                    composable(
                        "details/{item}",
                        arguments = listOf(navArgument("item") { type = NavType.StringType }),
                    ) {
                        val viewModel = hiltViewModel<ProductDetailsViewModel>()
                        ProductDetailsScreen(viewModel) {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}
