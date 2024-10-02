package com.siri.sample.ui.details.section

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.siri.core.R
import com.siri.core.data.model.Product
import com.siri.core.data.model.User
import com.siri.core.util.Dimens

/** 5/12/2023. */

@OptIn(ExperimentalPagerApi::class)
@Composable
fun UserProfileImage(user: User) {
    val item = user.image
    val pagerState = rememberPagerState()

    HorizontalPager(
        count = 1, // Update to load array of images
        state = pagerState
    ) { page ->
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item)
                .placeholder(R.drawable.logo_sample)
                .crossfade(true)
                .build(),
            loading = {
                CircularProgressIndicator(
                    color = Color.LightGray,
                    modifier = Modifier.padding(48.dp)
                )
            },
            contentDescription = stringResource(R.string.product_thumbnail),
            contentScale = ContentScale.Fit,
            modifier = Modifier.height(260.dp)
        )
    }
    Spacer(modifier = Modifier.size(Dimens.dp8))
    HorizontalTabs(
        items = listOf(item),
        pagerState = pagerState
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HorizontalTabs(
    items: List<String?>?,
    pagerState: PagerState,
) {
    val dotRadius = 4.dp
    val dotSpacing = 8.dp

    Box(
        modifier = Modifier
            .height(dotRadius * 2)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            horizontalArrangement = Arrangement.spacedBy(dotSpacing),
        ) {
            items?.forEachIndexed { index, _ ->
                val animatedWidth by animateIntAsState(
                    targetValue = if (pagerState.currentPage == index) dotRadius.value.toInt() * 12 else dotRadius.value.toInt() * 2, label = ""
                )
                Box(
                    modifier = Modifier
                        .height(dotRadius * 2)
                        .width(animatedWidth.dp)
                        .clip(
                            if (pagerState.currentPage == index) {
                                RoundedCornerShape(dotRadius)
                            } else {
                                CircleShape
                            }
                        )
                        .background(
                            if (pagerState.currentPage == index) Color.Gray else Color.LightGray
                        ),
                )
            }
        }
    }
}
