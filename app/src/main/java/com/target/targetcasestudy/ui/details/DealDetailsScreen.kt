package com.target.targetcasestudy.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.target.targetcasestudy.R
import com.target.targetcasestudy.api.Deal
import com.target.targetcasestudy.ui.theme.MyCustomBlack
import com.target.targetcasestudy.ui.theme.MyCustomDarkGray
import com.target.targetcasestudy.ui.theme.MyCustomGray
import com.target.targetcasestudy.ui.theme.MyCustomLightGray
import com.target.targetcasestudy.ui.theme.MyCustomLightRed
import com.target.targetcasestudy.ui.theme.MyCustomRed
import com.target.targetcasestudy.ui.theme.MyCustomWhite
import com.target.targetcasestudy.ui.theme.robotoFontFamily

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel,
    onBackClicked: () -> Unit,
) {
    val uiState by viewModel.productDetailsUiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.details),
                        color = MyCustomDarkGray,
                        fontWeight = FontWeight.Bold,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClicked() }) {
                        Icon(
                            painterResource(id = R.drawable.back_arrow),
                            contentDescription = stringResource(id = R.string.back_arrow_button_content_description),
                            tint = MyCustomLightRed,
                        )
                    }
                },
                backgroundColor = MyCustomWhite,
                contentColor = MyCustomWhite,
            )
        },
    ) { padding ->
        uiState?.let { uiState ->
            ProductDetails(Modifier.padding(padding), uiState)
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProductDetails(
    modifier: Modifier,
    deal: Deal,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 40.dp, start = 8.dp, end = 8.dp )
        ) {
            Image(
                painter = rememberImagePainter(deal.imageUrl),
                contentDescription = stringResource(id = R.string.product_image_content_description),
                modifier =
                Modifier
                    .fillMaxWidth()
                    .height(328.dp),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = deal.title,
                fontFamily = robotoFontFamily,
                fontSize = 18.sp,
                color = MyCustomBlack,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(
                    text = deal.salePrice?.displayString ?: stringResource(id = R.string.default_sale_price),
                    fontFamily = robotoFontFamily,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = MyCustomRed,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier.align(Alignment.Bottom),
                    text =
                    stringResource(id = R.string.reg) + " " + (
                            deal.regularPrice?.displayString
                                ?: stringResource(id = R.string.default_regular_price)
                            ),
                    fontFamily = robotoFontFamily,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = MyCustomDarkGray,
                )
            }
            Text(
                text = deal.fulfillment,
                fontSize = 14.sp,
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.Normal,
                color = MyCustomGray,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = MyCustomDarkGray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(id = R.string.product_details),
                fontFamily = robotoFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MyCustomDarkGray,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = deal.description,
                fontFamily = robotoFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = MyCustomLightGray,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Button(
            onClick = { /* Handle add to cart click */ },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(44.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MyCustomLightRed),
        ) {
            Text(
                fontFamily = robotoFontFamily,
                text = stringResource(id = R.string.add_to_cart),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MyCustomWhite,
                textAlign = TextAlign.Center,
            )
        }
    }
}