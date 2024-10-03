package com.siri.user.ui.details.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.siri.core.data.model.User
import com.siri.core.ui.theme.Gray200
import com.siri.core.util.Dimens

@Composable
fun UserDetailContent(user: User) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .background(color = Color.White),
    ) {
        item {
            UserProfileImage(user = user)
            Spacer(modifier = Modifier.size(Dimens.dp8))
            UserFirstName(user = user)
            Divider(color = Gray200, thickness = 10.dp)
//            DescriptionProduct(product = user)
        }
    }
}