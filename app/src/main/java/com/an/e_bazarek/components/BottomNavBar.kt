package com.an.e_bazarek

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.an.e_bazarek.model.BottomNavigationItem
import com.an.e_bazarek.model.Screen

@Composable
fun BottomNavBar(
    selectedItem: Int,
    navController: NavController,
    onSelectItem: (Int, String) -> Unit
) {
    val bottomNavItems = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
            route = Screen.Profile.route
        ),
        BottomNavigationItem(
            title = "MarketPlace",
            selectedIcon = Icons.Filled.List,
            unselectedIcon = Icons.Outlined.List,
            hasNews = false,
            route = Screen.Market.route
        ),
        BottomNavigationItem(
            title = "Messages",
            selectedIcon = Icons.Filled.Email,
            unselectedIcon = Icons.Outlined.Email,
            hasNews = false,
            route = Screen.Chat.route
        )
    )

    BottomNavigation() {
        bottomNavItems.forEachIndexed { index, item ->
            BottomNavigationItem(
                selected = selectedItem == index,
                onClick = { onSelectItem(index, item.route) },
                label = { Text(text = item.title) },
                icon = {
                    if(selectedItem == index)
                        Icon(
                            imageVector = item.selectedIcon,
                            contentDescription = null
                        )
                    else
                        Icon(
                            imageVector = item.unselectedIcon,
                            contentDescription = null
                        )

                }
            )
        }
    }

}