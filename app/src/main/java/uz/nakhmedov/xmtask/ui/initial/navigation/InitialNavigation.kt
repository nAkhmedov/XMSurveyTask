package uz.nakhmedov.xmtask.ui.initial.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import uz.nakhmedov.xmtask.ui.initial.InitialScreen
import uz.nakhmedov.xmtask.ui.main.navigation.mainNavigationRoute

const val initialNavigationRoute = "initial_route"

fun NavGraphBuilder.initialScreen(onGetStartClick: () -> Unit) {
    composable(
        route = initialNavigationRoute
    ) {
        InitialScreen(onGetStartClick)
    }
}

fun NavController.navigateToMain() {
    this.navigate(mainNavigationRoute)
}