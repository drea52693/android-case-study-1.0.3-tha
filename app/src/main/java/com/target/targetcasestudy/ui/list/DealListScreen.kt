package com.target.targetcasestudy.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.target.targetcasestudy.api.Deal
import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.theme.MyCustomBlack
import com.target.targetcasestudy.ui.theme.MyCustomDarkGray
import com.target.targetcasestudy.ui.theme.MyCustomGray
import com.target.targetcasestudy.ui.theme.MyCustomGreen
import com.target.targetcasestudy.ui.theme.MyCustomLightGray
import com.target.targetcasestudy.ui.theme.MyCustomRed
import com.target.targetcasestudy.ui.theme.MyCustomWhite
import com.target.targetcasestudy.ui.theme.robotoFontFamily

@Composable
fun DealListScreen(dealList: List<Deal>, dealClicked: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .padding(12.dp)
            .background(MyCustomWhite)
            .fillMaxWidth()
    ) {
        items(dealList) { deal ->
            DealItem(deal, dealClicked)
            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = MyCustomLightGray, thickness = 1.dp)
        }
    }
}

@Composable
fun DealItem(uiState: Deal, onDealItemClicked: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onDealItemClicked(uiState.id.toString()) }
            .padding(top = 16.dp, bottom = 8.dp)
    ) {
        LoadImageFromUrl(uiState.imageUrl)
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Row {
                Text(
                    text = uiState.salePrice?.displayString ?: stringResource(id = R.string.default_sale_price),
                    fontFamily = robotoFontFamily,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = MyCustomRed
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier.align(Alignment.Bottom),
                    fontFamily = robotoFontFamily,
                    text = stringResource(id = R.string.reg) + " " + (uiState.regularPrice?.displayString
                        ?: stringResource(
                            id = R.string.default_regular_price
                        )),
                    fontSize = 12.sp,
                    color = MyCustomDarkGray,
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = uiState.fulfillment,
                fontFamily = robotoFontFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = MyCustomGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = uiState.title,
                fontFamily = robotoFontFamily,
                fontSize = 14.sp,
                color = MyCustomBlack
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(
                    fontFamily = robotoFontFamily,
                    text = uiState.availability,
                    fontSize = 16.sp,
                    color = MyCustomGreen,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    fontFamily = robotoFontFamily,
                    text = stringResource(id = R.string.in_aisle) + " " + uiState.aisle,
                    fontSize = 16.sp,
                    color = MyCustomGray,
                )
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun LoadImageFromUrl(imageUrl: String) {
    val painter: Painter = rememberImagePainter(imageUrl)
    Image(
        painter = painter,
        contentDescription = stringResource(id = R.string.product_image_content_description),
        modifier = Modifier.size(140.dp),
    )
}