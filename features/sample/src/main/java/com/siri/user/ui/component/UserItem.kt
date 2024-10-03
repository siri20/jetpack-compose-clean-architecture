package com.siri.user.ui.component


import android.graphics.Bitmap
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.siri.core.R
import com.siri.core.data.model.User
import com.siri.core.ui.theme.Gray200

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    user: User = User()
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier
            .padding(8.dp)
            .defaultMinSize()
    ) {
        Column(
            modifier = modifier.defaultMinSize()
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.image)
                    .placeholder(R.drawable.logo_sample)
                    .crossfade(true)
                    .size(150, 150)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build(),
                loading = {
                    CircularProgressIndicator(
                        color = Color.LightGray,
                        modifier = Modifier.padding(48.dp)
                    )
                },
                contentDescription = stringResource(R.string.product_thumbnail),
                contentScale = ContentScale.Crop,
                modifier = modifier.height(180.dp)
            )
            Divider(color = Gray200, thickness = 1.dp)
            Column(
                modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = user.firstName ?: "",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = Color.Black
                )
                Text(
                    text = user.university,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserItemPreview() {
    UserItem(
        user = User(
            id = 1,
            firstName = "First Name",
            university = "UT Austin",
            image = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"
        ),
        modifier = Modifier
    )
}