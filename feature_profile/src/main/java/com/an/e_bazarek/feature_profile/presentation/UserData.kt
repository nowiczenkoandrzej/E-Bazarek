package com.an.e_bazarek.feature_profile.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.an.e_bazarek.shared_resources.domain.model.User

@Composable
fun UserData(
    user: User,
    amountOfListings: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        if(user.photoUrl != null) {
            AsyncImage(
                model = user.photoUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
            )
        }
        Column() {
            Text(text = user.username)
            Text(text = user.email)
            Text(text = "Amount of listings: $amountOfListings")
        }

    }
}

@Preview
@Composable
fun MyComposablePreview() {
    UserData(
        user = User(
            "123",
            "test@gmail.com",
            "artur",
            null
        ),
        amountOfListings = 10
    )
}
