package com.rivaldy.id.compose.ui.screen.detail.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rivaldy.id.core.R
import com.rivaldy.id.core.data.model.Product

/** Created by github.com/im-o on 5/12/2023. */

@Composable
internal fun DescriptionProduct(product: Product) {
    Column(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 16.dp
        )
    ) {
        Text(
            text = stringResource(R.string.description),
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.Normal, fontSize = 18.sp
            ),
            color = Color.Black
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = product.description ?: stringResource(R.string.dash),
            style = MaterialTheme.typography.subtitle2.copy(
                fontWeight = FontWeight.Light, fontSize = 16.sp
            ),
            color = Color.DarkGray
        )
    }
}