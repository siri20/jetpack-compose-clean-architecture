package com.siri.sample.ui.landing.section

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.siri.core.data.model.User
import com.siri.sample.ui.component.EmptyUsers
import com.siri.sample.ui.component.UserItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LandingContent(
    modifier: Modifier,
    listUsers: MutableList<User>?,
    navigateToDetail: (Int) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        if (listUsers != null) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(140.dp),
                content = {
                    items(listUsers, key = { it.id ?: -1 }) { user ->
                        UserItem(
                            user = user,
                            modifier = modifier
                                .fillMaxWidth()
                                .animateItemPlacement(tween(durationMillis = 100))
                                .clickable {
                                    navigateToDetail(user.id ?: return@clickable)
                                }
                        )
                    }
                }, contentPadding = PaddingValues(8.dp)
            )
            if (listUsers.isEmpty()) EmptyUsers()
        } else EmptyUsers()
    }
}