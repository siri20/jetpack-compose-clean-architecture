package com.siri.sample.ui.details.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.siri.core.data.model.User

@Composable
fun UserFirstName(user: User) {
    Column(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 8.dp
        )
    ) {
        Text(
            text = user.firstName,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Normal, fontSize = 28.sp
            ),
            color = Color.Black
        )
        Text(
            text = user.university,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Light, fontSize = 20.sp
            ),
            color = MaterialTheme.colorScheme.secondary
        )
    }
}