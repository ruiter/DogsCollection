package com.rmmobile.dogscollection.features.home

import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.rmmobile.dogscollection.R
import com.rmmobile.dogscollection.features.search.SearchScreen

@Composable
fun HomeScreen(viewModel: MainViewModel, navController: NavController) {
    val selectedTab = HomeTab.getTabFromResource(viewModel.selectedTab.value)

    val tabs = HomeTab.entries.toTypedArray()

    Scaffold(bottomBar = {
        BottomNavigation(
            modifier = Modifier.navigationBarsPadding()
        ) {
            tabs.forEach { tab ->
                BottomNavigationItem(
                    selected = tab == selectedTab,
                    onClick = { viewModel.selectTab(tab.title) },
                    icon = {
                        Icon(
                            imageVector = tab.icon,
                            contentDescription = "",
                            tint = if (selectedTab == tab) Color.White else Color.Gray
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = tab.title),
                            color = if (selectedTab == tab) Color.White else Color.Gray
                        )
                    },
                    selectedContentColor = LocalContentColor.current,
                    unselectedContentColor = LocalContentColor.current
                )
            }
        }
    }) { innerPadding ->

        Crossfade(selectedTab, label = "home_tab") {
            when (it) {
                HomeTab.HOME -> HomeContent()
                HomeTab.SEARCH -> SearchScreen()
            }
        }
    }

}

enum class HomeTab(
    @StringRes val title: Int,
    val icon: ImageVector
) {
    HOME(R.string.home_menu, Icons.Filled.Home),
    SEARCH(R.string.search_menu, Icons.Filled.Search);
    companion object {
        fun getTabFromResource(@StringRes resource: Int): HomeTab {
            return when (resource) {
                R.string.search_menu -> SEARCH
                else -> HOME
            }
        }
    }
}