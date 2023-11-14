package uz.nakhmedov.xmtask.ui.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import uz.nakhmedov.xmtask.ui.main.MainScreen

const val mainNavigationRoute = "main_route"

fun NavGraphBuilder.mainScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = mainNavigationRoute
    ) {
        MainScreen(onBackClick)
    }
}